package co.com.bcs.redebanclient.services.dto;

import lombok.Data;

@Data
public class DatosCompra {
    
String tipoDocumento;
String numeroDocumento;
String numeroTarjeta;
String cvcTarjeta;
String fechaExpiracion;
String franquicia;
String cuotas;
String monto;
String referencia;
String codigoTransaccion;

}
