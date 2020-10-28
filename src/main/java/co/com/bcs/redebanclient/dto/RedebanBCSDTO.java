package co.com.bcs.redebanclient.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RedebanBCSDTO {

	private String tipoId;

	private String numeroId;

	private String referencia;

	private Timestamp fecha;

	private String monto;

	private String metodo;

	private String estado;

	private String mensajeError;

	private String request;

	private String response;
}
