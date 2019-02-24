package br.com.arquitetura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class FieldRequiredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FieldRequiredException(String field) {
		super("O campo " + field + " é obrigatório");
	}
	
}
