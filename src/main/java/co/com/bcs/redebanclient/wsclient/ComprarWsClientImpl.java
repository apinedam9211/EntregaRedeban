package co.com.bcs.redebanclient.wsclient;

import javax.xml.bind.JAXBElement;

import co.com.bcs.wsclient.objects.comprarprocesar.*;

public class ComprarWsClientImpl extends ComprarWSClient {

    @Override
    public TipoRespuesta comprar(TipoSolicitudCompra request) {
        JAXBElement<TipoSolicitudCompra> requestJAXB = new ObjectFactory().createCompraProcesarSolicitud(request);
        JAXBElement<TipoRespuesta> response = (JAXBElement<TipoRespuesta>) this.getWebServiceTemplate()
                .marshalSendAndReceive(requestJAXB);
        return response.getValue();
    }

    @Override
    public TipoRespuesta reversarCompra(TipoSolicitudCompra request) {
        JAXBElement<TipoSolicitudCompra> requestJAXB = new ObjectFactory().createCompraReversarSolicitud(request);
        JAXBElement<TipoRespuesta> response = (JAXBElement<TipoRespuesta>) this.getWebServiceTemplate()
                .marshalSendAndReceive(requestJAXB);
        return response.getValue();
    }

}
