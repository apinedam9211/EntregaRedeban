package co.com.bcs.redebanclient.services;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import co.com.bcs.redebanclient.error.ErroresTecnicosEnum;
import co.com.bcs.redebanclient.exception.BackendException;
import co.com.bcs.redebanclient.mapper.ServiceMapper;
import co.com.bcs.redebanclient.services.dto.DatosCompra;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.redebanclient.services.dto.ReversoCompra;
import co.com.bcs.redebanclient.wsclient.ComprarWSClient;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoRespuesta;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoRespuesta;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoSolicitudCompra;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

	private ServiceMapper serviceMapper;

	private ComprarWSClient comprarWSClient;

	@Override
	public InformacionCompra comprar(DatosCompra compra) {
		TipoSolicitudCompra request = serviceMapper.toSolicitudCompra(compra);
		TipoRespuesta respuesta = comprarWSClient.comprar(request);
		if (isErrorTecnico(respuesta)) {
			log.error("SE PRESENTO UN ERROR TECNICO CON WS_REDEBAN");
			// TODO. enviar a la cola, devolver excepcion
			throw new BackendException();
		}
		return procesarRespuesta(respuesta);
	}

	@Override
	public InformacionCompra reversarComprar(ReversoCompra compra) {

		TipoSolicitudCompra request = serviceMapper.toSolicitudCompra(compra);
		TipoRespuesta respuesta = comprarWSClient.reversarCompra(request);
		// TODO. validar respueta pra indicarle la respueta
		if (isErrorTecnico(respuesta)) {
			// TODO. enviar a la cola, devolver excepcion
			throw new BackendException();
		}
		return procesarRespuesta(respuesta);
	}

	// TODO. validar si el error recibido es tecnico o no
	private boolean isErrorTecnico(TipoRespuesta respuesta) {
		TipoInfoRespuesta infoRespuesta = Optional.ofNullable(respuesta).map(TipoRespuesta::getInfoRespuesta)
				.orElseThrow(BackendException::new);
		String mensajeError = ErroresTecnicosEnum.findErrorTecnico(infoRespuesta.getCodRespuesta());
		if (null == mensajeError) {
			return false;
		} else {
			log.error("SE PRESENTO UN ERROR TECNICO CON WS_REDEBAN: " + mensajeError + " / "
					+ infoRespuesta.getCodRespuesta());
			throw new BackendException(mensajeError, infoRespuesta.getCodRespuesta());
		}
	}

	private InformacionCompra procesarRespuesta(TipoRespuesta respuesta) {

		TipoInfoRespuesta infoRespuesta = Optional.ofNullable(respuesta).map(TipoRespuesta::getInfoRespuesta)
				.orElseThrow(BackendException::new);

		if ("aprobado".equalsIgnoreCase(infoRespuesta.getEstado())) {
			return serviceMapper.toInformacionCompra(respuesta);
		} else {
			throw new BackendException(infoRespuesta.getDescRespuesta(), infoRespuesta.getCodRespuesta());
		}

	}

}
