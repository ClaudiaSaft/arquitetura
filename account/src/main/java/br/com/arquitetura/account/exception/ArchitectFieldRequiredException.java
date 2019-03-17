package br.com.arquitetura.account.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class ArchitectFieldRequiredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ArchitectFieldRequiredException(String field) {
		super("O campo architect " + field + " é obrigatório");
	}
	
	public ArchitectFieldRequiredException(String[] fields) {
		super("Pelo menos um dos campos a seguir devem ser preenchidos: achitect " + Arrays.asList(fields).stream().collect(Collectors.joining(", ")));
	}

}
