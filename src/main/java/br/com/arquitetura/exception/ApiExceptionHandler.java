package br.com.arquitetura.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.data.ErrorResponse;

@ControllerAdvice(annotations = RestController.class)
@RequestMapping
public class ApiExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception ex){
		LOGGER.info("Exception Occured:: URL = " + request.getRequestURL());
		ex.printStackTrace();
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getName(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
