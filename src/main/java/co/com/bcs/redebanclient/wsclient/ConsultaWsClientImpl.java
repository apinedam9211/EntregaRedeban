package co.com.bcs.redebanclient.wsclient;


import javax.xml.bind.JAXBElement;


import co.com.bcs.wsclient.objects.consulta.ConsultarEstadoDePagoRespuesta;
import co.com.bcs.wsclient.objects.consulta.ConsultarEstadoDePagoSolicitud;
import co.com.bcs.wsclient.objects.consulta.ObjectFactory;

public class ConsultaWsClientImpl extends ConsultaWsClient  {
    
    public ConsultarEstadoDePagoRespuesta consultarPago(ConsultarEstadoDePagoSolicitud request) {

        JAXBElement<ConsultarEstadoDePagoSolicitud> requestJAXB = new ObjectFactory()
                .createConsultarEstadoDePagoSolicitud(request);
        JAXBElement<ConsultarEstadoDePagoRespuesta> response = (JAXBElement<ConsultarEstadoDePagoRespuesta>) this
                .getWebServiceTemplate().marshalSendAndReceive(requestJAXB);
        return response.getValue();

    }

}
