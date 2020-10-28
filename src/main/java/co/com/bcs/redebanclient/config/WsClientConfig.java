package co.com.bcs.redebanclient.config;

import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import co.com.bcs.redebanclient.interceptor.LoggingClientWSInterceptor;
import co.com.bcs.redebanclient.wsclient.AnularWsClient;
import co.com.bcs.redebanclient.wsclient.AnularWsClientImpl;
import co.com.bcs.redebanclient.wsclient.ComprarWSClient;
import co.com.bcs.redebanclient.wsclient.ComprarWsClientImpl;
import co.com.bcs.redebanclient.wsclient.ConsultaWsClient;
import co.com.bcs.redebanclient.wsclient.ConsultaWsClientImpl;

@Configuration
public class WsClientConfig {

    @Value("${ws.redeban.uri.consulta}")
    private String uriConsultaRedeban;

    @Value("${ws.redeban.uri.compra}")
    private String uriCompraRedeban;

    @Value("${ws.redeban.username}")
    private String securementUsername;

    @Value("${ws.redeban.password}")
    private String securementPassword;

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
        security.setSecurementActions(WSHandlerConstants.USERNAME_TOKEN);
        security.setSecurementPasswordType(WSConstants.PW_TEXT);
        security.setSecurementUsername(securementUsername);
        security.setSecurementPassword(securementPassword);

        return security;
    }

    private Jaxb2Marshaller consultaMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("co.com.bcs.wsclient.objects.consulta");
        return marshaller;
    }

    private Jaxb2Marshaller compraMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("co.com.bcs.wsclient.objects.comprarprocesar");
        return marshaller;
    }

    private Jaxb2Marshaller anulacionMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("co.com.bcs.wsclient.objects.anulacion");
        return marshaller;
    }

    @Bean
    public ConsultaWsClient consultaWsClient(){
        ConsultaWsClient consultaWsClient = new ConsultaWsClientImpl();
        consultaWsClient.setInterceptors(new ClientInterceptor[]{securityInterceptor() , loggingClientWSInterceptor()});
        consultaWsClient.setDefaultUri(uriConsultaRedeban);
        consultaWsClient.setMarshaller(consultaMarshaller());
        consultaWsClient.setUnmarshaller(consultaMarshaller());
        return consultaWsClient;
    }

    @Bean
    public ComprarWSClient comprarWSClient(){
        ComprarWSClient wsClient = new ComprarWsClientImpl();
        wsClient.setInterceptors(new ClientInterceptor[]{securityInterceptor() , loggingClientWSInterceptor()});
        wsClient.setDefaultUri(uriCompraRedeban);
        wsClient.setMarshaller(compraMarshaller());
        wsClient.setUnmarshaller(compraMarshaller());
        return wsClient;
    }

    @Bean
    public AnularWsClient anularWsClient(){
        AnularWsClient wsClient = new AnularWsClientImpl();
        wsClient.setInterceptors(new ClientInterceptor[]{securityInterceptor() , loggingClientWSInterceptor()});
        wsClient.setDefaultUri(uriCompraRedeban);
        wsClient.setMarshaller(anulacionMarshaller());
        wsClient.setUnmarshaller(anulacionMarshaller());
        return wsClient;
    }

    @Bean
    public LoggingClientWSInterceptor loggingClientWSInterceptor(){
        return new LoggingClientWSInterceptor();
    }

}