package co.com.bcs.redebanclient.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.tools.JavaFileObject;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.HttpRequest;
import org.zalando.logbook.HttpResponse;
import org.zalando.logbook.Precorrelation;
import org.zalando.logbook.Sink;

import co.com.bcs.redebanclient.dto.AuditDTO;
import co.com.bcs.redebanclient.services.LoggingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SinkDatabase implements Sink {

	LoggingService loggingService;
	HttpLogFormatter httpLogFormatter;

	@Override
	public void write(Precorrelation precorrelation, HttpRequest request) throws IOException {
		log.debug("Log Request:" + httpLogFormatter.format(precorrelation, request));
	}

	@Override
	public void write(Correlation correlation, HttpRequest request, HttpResponse response) throws IOException {
		log.debug("Log Response" + httpLogFormatter.format(correlation, response));
		try {

			String tipoId = "";
			String numeroId = "";
			JSONObject json = requestParamsToJSON((ServletRequest) request);

			try {
				tipoId = json.getJSONObject("TipoIdPersona").getString("tipoDocumento");
				numeroId = json.getJSONObject("TipoIdPersona").getString("numDocumento");
			} catch (Exception e) {
				tipoId = "";
				numeroId = "";
			}

			AuditDTO auditDTO = new AuditDTO();
			auditDTO.setFinal_time(correlation.getEnd().toEpochMilli());
			auditDTO.setHttpMethod(request.getMethod());
			auditDTO.setInit_time(correlation.getStart().toEpochMilli());
			auditDTO.setIp(request.getRemote());
			auditDTO.setTipoId(tipoId);
			auditDTO.setNumeroId(numeroId);
			auditDTO.setRequest(httpLogFormatter.format(correlation, request));
			auditDTO.setResponse(httpLogFormatter.format(correlation, response));
			auditDTO.setResult(String.valueOf(response.getStatus()));
			auditDTO.setTransaccion(request.getPath());
			loggingService.auditar(auditDTO);
		}

		catch (Exception e) {
			log.error("error al auditar", e);
		}

	}

	public JSONObject requestParamsToJSON(ServletRequest req) {
		JSONObject jsonObj = new JSONObject();
		Map<String, String[]> params = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String v[] = entry.getValue();
			Object o = (v.length == 1) ? v[0] : v;
			jsonObj.put(entry.getKey(), o);
		}
		return jsonObj;
	}

}