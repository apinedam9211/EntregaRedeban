package co.com.bcs.redebanclient.controller.v1.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Compra {

@Valid
@NotNull
Cliente cliente;

@Valid
@NotNull
Tarjeta tarjeta;

@Valid
@NotNull
Pago pago;

}
