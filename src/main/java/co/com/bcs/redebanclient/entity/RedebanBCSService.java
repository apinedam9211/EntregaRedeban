package co.com.bcs.redebanclient.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "REDEBAN_BCS_LOG")
@Data
public class RedebanBCSService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "TIPO_ID")
	private String tipoId;

	@Column(name = "NUMERO_ID")
	private String numeroId;

	@Column(name = "REFERENCIA")
	private String referencia;

	@Column(name = "FECHA")
	private Timestamp fecha;

	@Column(name = "MONTO")
	private String monto;

	@Column(name = "METODO")
	private String metodo;

	@Column(name = "ESTADO")
	private String estado;

	@Column(name = "MENSAJE_ERROR")
	@Lob
	private String mensajeError;

	@Column(name = "REQUEST")
	@Lob
	private String request;

	@Column(name = "RESPONSE")
	@Lob
	private String response;
}
