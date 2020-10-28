package co.com.bcs.redebanclient.controller.v1.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Tarjeta {

@NotNull
Long numero;

@NotNull
String cvc;

@Pattern(regexp = "^[0-9]{2}$")
@NotNull
String mesExpiracion;

@Pattern(regexp = "^[0-9]{4}$")
@NotNull
String anioExpiracion;

@NotNull
Franquicia franquicia;

}
