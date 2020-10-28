package co.com.bcs.redebanclient.services.dto;

import lombok.Data;

@Data
public class DatosAnulacion extends DatosCompra {
    
 private String numAprobacion;
 private String idTransaccionAutorizador;
 private String razonAnulacion;
 

}
