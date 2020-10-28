package co.com.bcs.redebanClient.wsclient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.Source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreators;
import org.springframework.xml.transform.StringSource;

import co.com.bcs.redebanclient.wsclient.ComprarWSClient;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoCabeceraSolicitud;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoCapacidadPIN;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoIdPersona;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoIdTarjetaCredito;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoCompra;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoImpuestos;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoMedioPago;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoPersona;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoInfoPuntoInteraccion;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoModoCapturaPAN;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoRespuesta;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoSolicitudCompra;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoTipoDocumento;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoTipoImpuesto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WsClientCompraTest {

    private MockWebServiceServer mockServer;

    @Autowired
    private ComprarWSClient comprarWSClient;

    @BeforeEach
    void init() {
        mockServer = MockWebServiceServer.createServer(comprarWSClient);
    }

    @Test
    void securityTest() throws DatatypeConfigurationException{

        Source requestPayload = new StringSource(
            "<ns3:compraProcesarSolicitud xmlns:ns3=\"http://www.rbm.com.co/esb/comercio/compra/\" xmlns:ns2=\"http://www.rbm.com.co/esb/comercio/\" xmlns:ns4=\"http://www.rbm.com.co/esb/\">" +
            "         <ns3:cabeceraSolicitud>" +
            "            <ns3:infoPuntoInteraccion>" +
            "               <ns2:tipoTerminal>WEB</ns2:tipoTerminal>" +
            "               <ns2:idTerminal>A0000001</ns2:idTerminal>" +
            "               <ns2:idAdquiriente>1000000001</ns2:idAdquiriente>" +
            "               <ns2:idTransaccionTerminal>100001</ns2:idTransaccionTerminal>" +
            "               <ns2:modoCapturaPAN>Manual</ns2:modoCapturaPAN>" +
            "               <ns2:capacidadPIN>Virtual</ns2:capacidadPIN>" +
            "            </ns3:infoPuntoInteraccion>" +
            "         </ns3:cabeceraSolicitud>" +
            "         <ns3:idPersona>" +
            "            <ns4:tipoDocumento>CC</ns4:tipoDocumento>" +
            "            <ns4:numDocumento>1000000001</ns4:numDocumento>" +
            "         </ns3:idPersona>" +
            "         <ns3:infoMedioPago>" +
            "            <ns3:idTarjetaCredito>" +
            "               <ns4:franquicia>MasterCard</ns4:franquicia>" +
            "               <ns4:numTarjeta>5303XXXXXXXXX783</ns4:numTarjeta>" +
            "               <ns4:fechaExpiracion>2023-02-01</ns4:fechaExpiracion>" +
            "               <ns4:codVerificacion>355</ns4:codVerificacion>" +
            "            </ns3:idTarjetaCredito>" +
            "         </ns3:infoMedioPago>" +
            "         <ns3:infoCompra>" +
            "            <ns3:montoTotal>6</ns3:montoTotal>" +
            "            <ns3:infoImpuestos>" +
            "               <ns4:tipoImpuesto>IVA</ns4:tipoImpuesto>" +
            "               <ns4:monto>1</ns4:monto>" +
            "            </ns3:infoImpuestos>" +
            "            <ns3:cantidadCuotas>1</ns3:cantidadCuotas>" +
            "         </ns3:infoCompra>" +
            "         <ns3:infoPersona>" +
            "            <ns4:direccion>CALLE 20</ns4:direccion>" +
            "            <ns4:ciudad>BOGOTA</ns4:ciudad>" +
            "            <ns4:departamento>CU</ns4:departamento>" +
            "            <ns4:emailComercio>correo@ejemplo.com</ns4:emailComercio>" +
            "            <ns4:telefonoFijo>8607050</ns4:telefonoFijo>" +
            "            <ns4:celular>30010203040</ns4:celular>" +
            "         </ns3:infoPersona>" +
            "      </ns3:compraProcesarSolicitud>");
        Source responsePayload = new StringSource(
                "<ns2:compraProcesarRespuesta xmlns:ns2=\"http://www.rbm.com.co/esb/comercio/compra/\"></ns2:compraProcesarRespuesta>");

        mockServer.expect(RequestMatchers.payload(requestPayload))
                .andRespond(ResponseCreators.withPayload(responsePayload));

        TipoSolicitudCompra request = buildRequest();

        TipoRespuesta response = comprarWSClient.comprar(request);
        assertNotNull(response);

        mockServer.verify();

    }

    private TipoSolicitudCompra buildRequest() throws DatatypeConfigurationException {

        TipoSolicitudCompra solicitud = new TipoSolicitudCompra();
        TipoIdPersona persona = new TipoIdPersona();
        persona.setNumDocumento(1000000001);
        persona.setTipoDocumento(TipoTipoDocumento.CC);
        solicitud.setIdPersona(persona);
        TipoCabeceraSolicitud cabecera = new TipoCabeceraSolicitud();
        TipoInfoPuntoInteraccion puntoInteraccion = new TipoInfoPuntoInteraccion();
        puntoInteraccion.setTipoTerminal("WEB");
        puntoInteraccion.setIdTerminal("A0000001");
        puntoInteraccion.setIdAdquiriente("1000000001");
        puntoInteraccion.setIdTransaccionTerminal(100001);
        puntoInteraccion.setModoCapturaPAN(TipoModoCapturaPAN.MANUAL);
        puntoInteraccion.setCapacidadPIN(TipoCapacidadPIN.VIRTUAL);
        cabecera.setInfoPuntoInteraccion(puntoInteraccion);
        solicitud.setCabeceraSolicitud(cabecera);

        TipoInfoCompra infoCompra = new TipoInfoCompra();
        infoCompra.setCantidadCuotas(1);
        infoCompra.setMontoTotal(BigDecimal.valueOf(6));
        TipoInfoImpuestos infoImpuestos = new TipoInfoImpuestos();
        infoImpuestos.setTipoImpuesto(TipoTipoImpuesto.IVA);
        infoImpuestos.setMonto(BigDecimal.ONE);
        infoCompra.getInfoImpuestos().add(infoImpuestos);
        solicitud.setInfoCompra(infoCompra);
        TipoInfoMedioPago infoMedioPago = new TipoInfoMedioPago();
        TipoIdTarjetaCredito tarjetaCredito = new TipoIdTarjetaCredito();
        tarjetaCredito.setCodVerificacion("355");
        tarjetaCredito.setNumTarjeta("5303XXXXXXXXX783");

       

        tarjetaCredito.setFechaExpiracion( DatatypeFactory.newInstance().newXMLGregorianCalendar("2023-02-01"));
        tarjetaCredito.setFranquicia("MasterCard");
        infoMedioPago.setIdTarjetaCredito(tarjetaCredito);
        solicitud.setInfoMedioPago(infoMedioPago);

        TipoInfoPersona infoPersona= new TipoInfoPersona();
        infoPersona.setDireccion("CALLE 20");
        infoPersona.setCiudad("BOGOTA");
        infoPersona.setDepartamento("CU");
        infoPersona.setEmailComercio("correo@ejemplo.com");
        infoPersona.setTelefonoFijo("8607050");
        infoPersona.setCelular("30010203040");
        solicitud.getInfoPersona().add(infoPersona);

return solicitud;
    }

}
