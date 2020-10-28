package co.com.bcs.redebanclient.error;

import java.util.HashMap;
import java.util.Map;

public enum ErroresTecnicosEnum {
	
	TIME_OUT("Time Out", "9001"), ERROR_CONEXION("Error de conexión", "9002"),
	ERROR_ESTRTC_WRONG("Error Detectado por Estructura Incorrecta", "9003"), ERROR_ESB("Error del ESB", "9004"),
	ERROR_DP("Error no repuesta DP", "9020"), ORI_NO_FOUND("Original no Encontrado", "9008"),
	ORI_NO_FOUND_2("Original no Encontrado", "9009");

	private static final Map<String, ErroresTecnicosEnum> BY_LABEL = new HashMap<>();
	private static final Map<String, ErroresTecnicosEnum> BY_ID = new HashMap<>();

	static {
		for (ErroresTecnicosEnum e : values()) {
			BY_LABEL.put(e.label, e);
			BY_ID.put(e.id, e);
		}
	}

	public final String label;
	public final String id;

	private ErroresTecnicosEnum(String label, String id) {
		this.label = label;
		this.id = id;
	}

	private static ErroresTecnicosEnum valueOfId(String id) {
		return BY_ID.get(id);
	}

	public static String findErrorTecnico(String id) {
		ErroresTecnicosEnum error = null;
		try {
			error = valueOfId(id);
		} catch (Exception e) {
			error = null;
		}
		if (error != null) {
			return error.label;
		} else {
			return null;
		}
	}

}