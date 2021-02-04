package br.com.cgms.grandpabot.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cgms.grandpabot.domain.exception.NotFoundBusinessException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NotFoundBusinessException.class)
	public ResponseEntity<Object> handleNotFoundBusinessException(NotFoundBusinessException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		var handler = new Handler();
		handler.setStatus(status.value());
		handler.setTitulo(ex.getMessage());
		handler.setDataHora(OffsetDateTime.now());		
				
		return super.handleExceptionInternal(ex, handler, new HttpHeaders(), status, request);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var handler = new Handler();
		handler.setStatus(status.value());
		handler.setTitulo("Um ou mais campos estão invalidos, preencha corretamente e tente novamente");
		handler.setDataHora(OffsetDateTime.now());
		handler.setErrors(new ArrayList<Handler.Error>());
		for (ObjectError error : ex.getAllErrors()) {
			String name; 
			String message;
			if (error instanceof FieldError) {
				name = ((FieldError) error).getField();
				message = error.getDefaultMessage();
			} else {
				name = error.getObjectName();
				message = error.getDefaultMessage();
			}
			handler.getErrors().add(new Handler.Error(name, message));
		}
		
		return super.handleExceptionInternal(ex, handler, headers, status, request);
	}
}
