package co.com.bcs.redebanclient.controller;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.bcs.redebanclient.controller.v1.entity.Error;
import co.com.bcs.redebanclient.exception.BackendException;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Optional<String> descripcionError = ex.getBindingResult().getFieldErrors().stream()
				.map(FieldError::getDefaultMessage).reduce((x, y) -> x + "," + y);
		return new ResponseEntity<>(
				Error.builder().descriptionError(descripcionError.orElse("En este momento no podemos atenderlo"))
						.errorCode("VAL").build(),
						HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BackendException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Error> backendException(BackendException ex, WebRequest request) {
		Error respuesta = Error.builder().descriptionError(ex.getDescripcionUsuario())
				.errorCode(ex.getDescripcionUsuario()).build();

		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> lastException(Exception ex, WebRequest request) {
		Error respuesta = Error.builder().descriptionError("En este momento no podemos atenderlo").errorCode("0001")
				.build();
		return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
