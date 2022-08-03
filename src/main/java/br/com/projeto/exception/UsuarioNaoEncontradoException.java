package br.com.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException(Integer id) {
		super("Nao foi possível encontrar o Usuário -> " + id );	
	}
}
