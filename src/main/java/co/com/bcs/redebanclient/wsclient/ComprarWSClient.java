package co.com.bcs.redebanclient.wsclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import co.com.bcs.wsclient.objects.comprarprocesar.TipoRespuesta;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoSolicitudCompra;

public abstract class ComprarWSClient extends WebServiceGatewaySupport{
    
    public abstract TipoRespuesta comprar(TipoSolicitudCompra request);

    public abstract  TipoRespuesta reversarCompra(TipoSolicitudCompra request);
    

}
