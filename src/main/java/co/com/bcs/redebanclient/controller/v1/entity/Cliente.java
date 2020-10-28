package co.com.bcs.redebanclient.controller.v1.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Cliente {

    @NotNull
    TipoDocumento tipoDocumento;

    @NotNull
    Long numeroDocumento;
}
