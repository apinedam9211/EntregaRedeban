package co.com.bcs.redebanclient.wsclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import co.com.bcs.wsclient.objects.anulacion.TipoRespuesta; 
import co.com.bcs.wsclient.objects.anulacion.TipoSolicitudCancelacion;

public abstract class AnularWsClient extends WebServiceGatewaySupport{
    

public abstract TipoRespuesta anulacion(TipoSolicitudCancelacion request);

public abstract TipoRespuesta reversoAnulacion(TipoSolicitudCancelacion request);

}
 