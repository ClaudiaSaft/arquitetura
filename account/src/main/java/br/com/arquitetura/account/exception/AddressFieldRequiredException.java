package br.com.arquitetura.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class AddressFieldRequiredException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AddressFieldRequiredException(String field) {
		super("O campo address " + field + " é obrigatório");
	}
	
}
