package co.com.bcs.redebanclient.services.dto;

import lombok.Data;

@Data
public class InformacionCompra {

    String fechaPosteo;
    String fechaTransaccion;
    String numeroAprobacion;
    String costoTransaccion;
    String idTransaccionAutorizador;

}
