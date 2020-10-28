package co.com.bcs.redebanclient.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO implements Serializable {

	private String tipoId;

	private String numeroId;
	
	private String request;

	private String response;

	private String ip;

	private String error_message;

	private long init_time;

	private long final_time;

	private String transaccion;

	private String result;

	private String httpMethod;

}
