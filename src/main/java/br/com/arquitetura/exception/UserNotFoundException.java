package br.com.arquitetura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR, reason="Usuário não encontrado")
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

}
