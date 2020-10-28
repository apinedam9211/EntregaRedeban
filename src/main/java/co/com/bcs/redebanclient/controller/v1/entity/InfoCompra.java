package co.com.bcs.redebanclient.controller.v1.entity;

import lombok.Data;

@Data
public class InfoCompra {
    
String fechaPosteo;

String fechaTransaccion;

String numeroAprobacion;

String costoTransaccion;

String idTransaccionAutorizador;
}
