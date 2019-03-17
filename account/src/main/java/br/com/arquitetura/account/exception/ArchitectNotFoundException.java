package br.com.arquitetura.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class ArchitectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ArchitectNotFoundException() {
		super("Arquiteto não encontrado");
	}
	
}
