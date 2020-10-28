package co.com.bcs.redebanclient.controller.v1.entity;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Pago {

    @NotNull
    Integer cuotas;
    @NotNull
    Long monto;
    @NotNull
    String referencia;

}
