package br.com.arquitetura.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class FieldRequiredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FieldRequiredException(String field) {
		super("O campo " + field + " é obrigatório");
	}

	public FieldRequiredException(String[] fields) {
		super("Pelo menos um dos campos a seguir devem ser preenchidos: " + Arrays.asList(fields).stream().collect(Collectors.joining(", ")));
	}
	
}
