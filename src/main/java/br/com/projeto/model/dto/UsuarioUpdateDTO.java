package br.com.projeto.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDTO {

	private String login;
	private String name;
	private String email;
	private LocalDate updatedDate;
	
	public UsuarioUpdateDTO(String login, String name, String email, LocalDate updatedDate) {
		super();
		this.login = login;
		this.name = name;
		this.email = email;
		this.updatedDate = updatedDate;
	}
	
	
}
