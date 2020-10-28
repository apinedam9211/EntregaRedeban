package co.com.bcs.redebanclient.wsclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import co.com.bcs.wsclient.objects.consulta.ConsultarEstadoDePagoRespuesta;
import co.com.bcs.wsclient.objects.consulta.ConsultarEstadoDePagoSolicitud;

public abstract class ConsultaWsClient  extends WebServiceGatewaySupport{
    public abstract ConsultarEstadoDePagoRespuesta consultarPago(ConsultarEstadoDePagoSolicitud request);
}
