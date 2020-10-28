package co.com.bcs.redebanclient.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import co.com.bcs.redebanclient.exception.BackendException;
import co.com.bcs.redebanclient.mapper.AnularMapper;
import co.com.bcs.redebanclient.mapper.ServiceMapper;
import co.com.bcs.redebanclient.services.dto.DatosAnulacion;
import co.com.bcs.redebanclient.services.dto.InformacionCompra;
import co.com.bcs.redebanclient.services.dto.ReversoAnulacion;
import co.com.bcs.redebanclient.wsclient.AnularWsClient;
import co.com.bcs.wsclient.objects.anulacion.TipoInfoRespuesta;
import co.com.bcs.wsclient.objects.anulacion.TipoRespuesta;
import co.com.bcs.wsclient.objects.anulacion.TipoSolicitudCancelacion;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class AnulacionServiceImpl implements AnulacionService {

    private AnularMapper mapper;

    private AnularWsClient anularWsClient;

    @Override
    public InformacionCompra anular(DatosAnulacion datosAnulacion) {
        

        TipoSolicitudCancelacion request = mapper.toTipoSolicitudCancelacion(datosAnulacion);
        TipoRespuesta respuesta = anularWsClient.anulacion(request);

        if (isErrorTecnico(respuesta)) {

            // TODO. enviar a la cola, devolver excepcion
            throw new BackendException();
        }

        return procesarRespuesta(respuesta);
    }

    private boolean isErrorTecnico(TipoRespuesta respuesta) {
        return false;
    }

    @Override
    public InformacionCompra reversarAnular(ReversoAnulacion reversoAnulacion) {

        TipoSolicitudCancelacion request = mapper.toTipoSolicitudCancelacion(reversoAnulacion);
        TipoRespuesta respuesta = anularWsClient.reversoAnulacion(request);

        return procesarRespuesta(respuesta);
    }

    private InformacionCompra procesarRespuesta(TipoRespuesta respuesta) {

        TipoInfoRespuesta infoRespuesta = Optional.ofNullable(respuesta).map(TipoRespuesta::getInfoRespuesta)
                .orElseThrow(BackendException::new);

        if ("aprobado".equalsIgnoreCase(infoRespuesta.getEstado())) {
            return mapper.toInformacionCompra(respuesta);
        } else {
            throw new BackendException(infoRespuesta.getDescRespuesta(), infoRespuesta.getCodRespuesta());
        }

    }

}
