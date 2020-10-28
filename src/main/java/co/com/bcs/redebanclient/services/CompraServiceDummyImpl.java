package co.com.bcs.redebanclient.services;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Optional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import co.com.bcs.redebanclient.exception.BackendException;
import co.com.bcs.redebanclient.mapper.ServiceMapper;
import co.com.bcs.redebanclient.services.dto.DatosCompra;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.redebanclient.services.dto.ReversoCompra;
import co.com.bcs.redebanclient.wsclient.ComprarWSClient;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoCabeceraSolicitud;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoCapacidadPIN;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoCompraResp;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoPuntoInteraccion;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoRespuesta;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoTerminal;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoUbicacion2;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoModoCapturaPAN;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoRespuesta;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoSolicitudCompra;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class CompraServiceDummyImpl implements CompraServiceDummy {

	private ServiceMapper serviceMapper;

	@Override
	public InformacionCompra comprar(DatosCompra compra) {

		if (!compra.getCodigoTransaccion().equalsIgnoreCase("0")) {
			TipoRespuesta respuesta = new TipoRespuesta();

			TipoCabeceraSolicitud cabeceraRespues = new TipoCabeceraSolicitud();
			TipoInfoPuntoInteraccion infoPuntoInterracion = new TipoInfoPuntoInteraccion();
			infoPuntoInterracion.setCapacidadPIN(TipoCapacidadPIN.VIRTUAL);
			infoPuntoInterracion.setModoCapturaPAN(TipoModoCapturaPAN.MANUAL);
			infoPuntoInterracion.setIdAdquiriente("88664422");
			infoPuntoInterracion.setIdTerminal("8866442200123456789");
			infoPuntoInterracion.setIdTransaccionTerminal(Long.valueOf("123456"));
			infoPuntoInterracion.setTipoTerminal("WEB");

			cabeceraRespues.setInfoPuntoInteraccion(infoPuntoInterracion);

			respuesta.setCabeceraRespuesta(cabeceraRespues);
			Long idTransaccionAutorizador = Long.valueOf("1010206206");
			respuesta.setIdTransaccionAutorizador(idTransaccionAutorizador);

			TipoInfoCompraResp infoCompraResp = new TipoInfoCompraResp();
			infoCompraResp.setCostoTransaccion(BigDecimal.valueOf(1000000, 00));
			try {
				infoCompraResp.setFechaPosteo(getXMLGregorianCalendarNow());
				infoCompraResp.setFechaTransaccion(getXMLGregorianCalendarNow());

			} catch (DatatypeConfigurationException e) {

			}
			infoCompraResp.setNumAprobacion("10002872673");
			respuesta.setInfoCompraResp(infoCompraResp);

			TipoInfoRespuesta infoRespuesta = new TipoInfoRespuesta();
			infoRespuesta.setCodRespuesta("00");
			infoRespuesta.setDescRespuesta("APROBADO");
			infoRespuesta.setEstado("APROBADO");
			respuesta.setInfoRespuesta(infoRespuesta);

			if (isErrorTecnico(respuesta)) {

				// TODO. enviar a la cola, devolver excepcion
				throw new BackendException();
			}
			return procesarRespuesta(respuesta);
		} else {
			return comprarER(compra);
		}
	}

	@Override
	public InformacionCompra reversarComprar(ReversoCompra compra) {
		if (!compra.getCodigoTransaccion().equalsIgnoreCase("0")) {
			TipoRespuesta respuesta = new TipoRespuesta();

			TipoCabeceraSolicitud cabeceraRespues = new TipoCabeceraSolicitud();
			TipoInfoPuntoInteraccion infoPuntoInterracion = new TipoInfoPuntoInteraccion();
			infoPuntoInterracion.setCapacidadPIN(TipoCapacidadPIN.VIRTUAL);
			infoPuntoInterracion.setModoCapturaPAN(TipoModoCapturaPAN.MANUAL);
			infoPuntoInterracion.setIdAdquiriente("88664422");
			infoPuntoInterracion.setIdTerminal("8866442200123456789");
			infoPuntoInterracion.setIdTransaccionTerminal(Long.valueOf("123456"));
			infoPuntoInterracion.setTipoTerminal("WEB");

			cabeceraRespues.setInfoPuntoInteraccion(infoPuntoInterracion);

			respuesta.setCabeceraRespuesta(cabeceraRespues);
			Long idTransaccionAutorizador = Long.valueOf("1010206206");
			respuesta.setIdTransaccionAutorizador(idTransaccionAutorizador);

			TipoInfoCompraResp infoCompraResp = new TipoInfoCompraResp();
			infoCompraResp.setCostoTransaccion(BigDecimal.valueOf(1000000, 00));
			try {
				infoCompraResp.setFechaPosteo(getXMLGregorianCalendarNow());
				infoCompraResp.setFechaTransaccion(getXMLGregorianCalendarNow());

			} catch (DatatypeConfigurationException e) {

			}
			infoCompraResp.setNumAprobacion("10002872673");
			respuesta.setInfoCompraResp(infoCompraResp);

			TipoInfoRespuesta infoRespuesta = new TipoInfoRespuesta();
			infoRespuesta.setCodRespuesta("00");
			infoRespuesta.setDescRespuesta("APROBADO");
			infoRespuesta.setEstado("APROBADO");
			respuesta.setInfoRespuesta(infoRespuesta);

			// TODO. validar respueta pra indicarle la respueta

			return procesarRespuesta(respuesta);
		} else {
			return reversarComprarER(compra);
		}
	}

	// TODO. validar si el error recibido es tecnico o no
	private boolean isErrorTecnico(TipoRespuesta respuesta) {
		return false;
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

	public XMLGregorianCalendar getXMLGregorianCalendarNow() throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		return now;
	}

	private InformacionCompra comprarER(DatosCompra compra) {

		TipoRespuesta respuesta = new TipoRespuesta();

		TipoCabeceraSolicitud cabeceraRespues = new TipoCabeceraSolicitud();
		TipoInfoPuntoInteraccion infoPuntoInterracion = new TipoInfoPuntoInteraccion();
		infoPuntoInterracion.setCapacidadPIN(TipoCapacidadPIN.VIRTUAL);
		infoPuntoInterracion.setModoCapturaPAN(TipoModoCapturaPAN.MANUAL);
		infoPuntoInterracion.setIdAdquiriente("88664422");
		infoPuntoInterracion.setIdTerminal("8866442200123456789");
		infoPuntoInterracion.setIdTransaccionTerminal(Long.valueOf("123456"));
		infoPuntoInterracion.setTipoTerminal("WEB");

		cabeceraRespues.setInfoPuntoInteraccion(infoPuntoInterracion);

		respuesta.setCabeceraRespuesta(cabeceraRespues);
		Long idTransaccionAutorizador = Long.valueOf("1010206206");
		respuesta.setIdTransaccionAutorizador(idTransaccionAutorizador);

		TipoInfoCompraResp infoCompraResp = new TipoInfoCompraResp();
		infoCompraResp.setCostoTransaccion(BigDecimal.valueOf(1000000, 00));
		try {
			infoCompraResp.setFechaPosteo(getXMLGregorianCalendarNow());
			infoCompraResp.setFechaTransaccion(getXMLGregorianCalendarNow());

		} catch (DatatypeConfigurationException e) {

		}
		infoCompraResp.setNumAprobacion("10002872673");
		respuesta.setInfoCompraResp(infoCompraResp);

		TipoInfoRespuesta infoRespuesta = new TipoInfoRespuesta();
		infoRespuesta.setCodRespuesta("07");
		infoRespuesta.setDescRespuesta("TARJETA RESTRINGIDA");
		infoRespuesta.setEstado("RECHAZADO");
		respuesta.setInfoRespuesta(infoRespuesta);

		if (isErrorTecnico(respuesta)) {

			// TODO. enviar a la cola, devolver excepcion
			throw new BackendException();
		}
		return procesarRespuesta(respuesta);
	}

	private InformacionCompra reversarComprarER(ReversoCompra compra) {

		TipoRespuesta respuesta = new TipoRespuesta();

		TipoCabeceraSolicitud cabeceraRespues = new TipoCabeceraSolicitud();
		TipoInfoPuntoInteraccion infoPuntoInterracion = new TipoInfoPuntoInteraccion();
		infoPuntoInterracion.setCapacidadPIN(TipoCapacidadPIN.VIRTUAL);
		infoPuntoInterracion.setModoCapturaPAN(TipoModoCapturaPAN.MANUAL);
		infoPuntoInterracion.setIdAdquiriente("88664422");
		infoPuntoInterracion.setIdTerminal("8866442200123456789");
		infoPuntoInterracion.setIdTransaccionTerminal(Long.valueOf("123456"));
		infoPuntoInterracion.setTipoTerminal("WEB");

		cabeceraRespues.setInfoPuntoInteraccion(infoPuntoInterracion);

		respuesta.setCabeceraRespuesta(cabeceraRespues);
		Long idTransaccionAutorizador = Long.valueOf("1010206206");
		respuesta.setIdTransaccionAutorizador(idTransaccionAutorizador);

		TipoInfoCompraResp infoCompraResp = new TipoInfoCompraResp();
		infoCompraResp.setCostoTransaccion(BigDecimal.valueOf(1000000, 00));
		try {
			infoCompraResp.setFechaPosteo(getXMLGregorianCalendarNow());
			infoCompraResp.setFechaTransaccion(getXMLGregorianCalendarNow());

		} catch (DatatypeConfigurationException e) {

		}
		infoCompraResp.setNumAprobacion("10002872673");
		respuesta.setInfoCompraResp(infoCompraResp);

		TipoInfoRespuesta infoRespuesta = new TipoInfoRespuesta();
		infoRespuesta.setCodRespuesta("111");
		infoRespuesta.setDescRespuesta("Error de estructura en el servicio (Error no parametrizado)");
		infoRespuesta.setEstado("RECHAZADO");
		respuesta.setInfoRespuesta(infoRespuesta);

		// TODO. validar respueta pra indicarle la respueta

		return procesarRespuesta(respuesta);
	}

}
