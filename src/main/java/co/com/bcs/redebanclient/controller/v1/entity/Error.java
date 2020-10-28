package co.com.bcs.redebanclient.controller.v1.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Error {
    
    @Schema(title= "Código de error, cuando status es ER")
    private String errorCode;
    @Schema(title= "Descripción de error, cuando status es ER")
    private String descriptionError;

    public String getErrorCode() {

        if (errorCode == null) {
            errorCode = "";
        }
        return errorCode;

    }

    public String getDescriptionError() {

        if (descriptionError == null) {
            descriptionError = "";
        }
        return descriptionError;

    }

}
