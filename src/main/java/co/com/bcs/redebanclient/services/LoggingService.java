package co.com.bcs.redebanclient.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.bcs.redebanclient.dto.AuditDTO;
import co.com.bcs.redebanclient.dto.RedebanBCSDTO;

public interface LoggingService {

	String logRequest(HttpServletRequest httpServletRequest, Object body);

	String logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);

	void auditRedebanBCSService(String response, RedebanBCSDTO redebanDAO);

	void auditar(AuditDTO auditDTO);
}
