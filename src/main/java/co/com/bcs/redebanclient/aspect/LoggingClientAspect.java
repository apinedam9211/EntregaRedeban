package co.com.bcs.redebanclient.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bcs.redebanclient.dto.RedebanBCSDTO;
import co.com.bcs.redebanclient.services.LoggingService;
import co.com.bcs.wsclient.objects.comprarprocesar.TipoSolicitudCompra;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Profile("weblogic")
public class LoggingClientAspect {

	@Autowired
	private LoggingService loggingService;

	@Pointcut("this(co.com.bcs.redebanclient.wsclient.ComprarWSClient)")
	public void applicationComprarWSClientPointcut() {

		// Method is empty as this is just a Pointcut, the implementations are in the
		// advices.
	}
	/*
	 * @Pointcut("this(co.com.bcs.redebanclient.wsclient.ComprarWSClient)") public
	 * void applicationAnularWsClientPointcut() {
	 * 
	 * // Method is empty as this is just a Pointcut, the implementations are in the
	 * // advices. }
	 */

	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */
	@AfterThrowing(pointcut = "applicationComprarWSClientPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");

	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@Around("applicationComprarWSClientPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		RedebanBCSDTO redebanDAO = new RedebanBCSDTO();
		Object resultResponse = null;
		String jsonResponse = "";
		log.debug("Inicia Aspect Logging Client");
		try {
			log.debug("Transaccion: " + joinPoint.getSignature().getDeclaringTypeName());
			log.debug("Metodo: " + joinPoint.getSignature().getName());

			/* Comprar */
			if (joinPoint.getSignature().getName().equalsIgnoreCase("comprar")) {
				redebanDAO = getComprarInfo(joinPoint);
			}
			/* Reversar */
			if (joinPoint.getSignature().getName().equalsIgnoreCase("reversarcompra")) {
				redebanDAO = getReversarCompraInfo(joinPoint);
			}

			log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), joinPoint.getArgs()[0].toString());

			redebanDAO.setFecha(new Timestamp(System.currentTimeMillis()));
			redebanDAO.setMetodo(joinPoint.getSignature().getName());
			redebanDAO.setEstado("OK");

			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(joinPoint.getArgs()[0]);
				redebanDAO.setRequest(json);
			} catch (JsonProcessingException e) {
				redebanDAO.setRequest(joinPoint.getArgs()[0].toString());

			}

			resultResponse = joinPoint.proceed();

			jsonResponse = getJSONResponse(resultResponse);

			log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), jsonResponse);

			loggingService.auditRedebanBCSService(jsonResponse, redebanDAO);
			return resultResponse;
		} catch (Exception e) {

			log.error("Illegal argument in LoggingClientAspect: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
					joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e);

			redebanDAO.setEstado("ERROR");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			redebanDAO.setMensajeError(exceptionAsString);
			loggingService.auditRedebanBCSService(jsonResponse, redebanDAO);
			throw e;
		}
	}

	private RedebanBCSDTO getReversarCompraInfo(ProceedingJoinPoint joinPoint) {
		RedebanBCSDTO redebanDAO = new RedebanBCSDTO();
		try {
			TipoSolicitudCompra result1 = (TipoSolicitudCompra) joinPoint.getArgs()[0];
			redebanDAO.setNumeroId(String.valueOf(result1.getIdPersona().getNumDocumento()));
			redebanDAO.setTipoId(result1.getIdPersona().getTipoDocumento().name());
			redebanDAO.setReferencia(result1.getInfoCompra().getReferencia());
			redebanDAO.setMonto(result1.getInfoCompra().getMontoTotal().toString());
			redebanDAO.setMetodo("ReversarComprar");
			log.debug("getComprarInfo Datos DAO OK");
			return redebanDAO;
		} catch (Exception e) {
			log.error("Error en getComprarInfo: " + e.toString(), e);
			return new RedebanBCSDTO();
		}
	}

	private RedebanBCSDTO getComprarInfo(ProceedingJoinPoint joinPoint) {
		RedebanBCSDTO redebanDAO = new RedebanBCSDTO();
		try {
			TipoSolicitudCompra result1 = (TipoSolicitudCompra) joinPoint.getArgs()[0];
			redebanDAO.setNumeroId(String.valueOf(result1.getIdPersona().getNumDocumento()));
			redebanDAO.setTipoId(result1.getIdPersona().getTipoDocumento().name());
			redebanDAO.setReferencia(result1.getInfoCompra().getReferencia());
			redebanDAO.setMonto(result1.getInfoCompra().getMontoTotal().toString());
			redebanDAO.setMetodo("Comprar");
			log.debug("getComprarInfo Datos DAO OK");
			return redebanDAO;
		} catch (Exception e) {
			log.error("Error en getComprarInfo: " + e.toString(), e);
			return new RedebanBCSDTO();
		}
	}

	private String getJSONResponse(Object result) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			json = "";
		}
		return json;
	}

}
