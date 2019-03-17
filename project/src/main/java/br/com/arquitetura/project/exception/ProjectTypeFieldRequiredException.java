package br.com.arquitetura.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class ProjectTypeFieldRequiredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProjectTypeFieldRequiredException(String field) {
		super("O campo project type " + field + " é obrigatório");
	}
	
}
