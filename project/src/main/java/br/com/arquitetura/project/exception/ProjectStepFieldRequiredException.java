package br.com.arquitetura.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class ProjectStepFieldRequiredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProjectStepFieldRequiredException(String field) {
		super("O campo project step " + field + " é obrigatório");
	}
	
}
