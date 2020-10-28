package co.com.bcs.redebanclient.wsclient;

import javax.xml.bind.JAXBElement;

import co.com.bcs.wsclient.objects.anulacion.*;

public class AnularWsClientImpl extends AnularWsClient {

    @Override
    public TipoRespuesta anulacion(TipoSolicitudCancelacion request) {

        JAXBElement<TipoSolicitudCancelacion> requestJAXB = new ObjectFactory()
                .createCompraCancelacionProcesarSolicitud(request);
        JAXBElement<TipoRespuesta> response = (JAXBElement<TipoRespuesta>) this.getWebServiceTemplate()
                .marshalSendAndReceive(requestJAXB);
        return response.getValue();
    }

    @Override
    public TipoRespuesta reversoAnulacion(TipoSolicitudCancelacion request) {
        JAXBElement<TipoSolicitudCancelacion> requestJAXB = new ObjectFactory()
                .createCompraCancelacionReversarSolicitud(request);
        JAXBElement<TipoRespuesta> response = (JAXBElement<TipoRespuesta>) this.getWebServiceTemplate()
                .marshalSendAndReceive(requestJAXB);
        return response.getValue();
    }

}
