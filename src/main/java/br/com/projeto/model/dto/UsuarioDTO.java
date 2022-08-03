package br.com.projeto.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Integer id;
    
	@NotBlank(message ="Campo não informado")
    @Pattern(regexp = "^[A-Z]+(.)*", message="O Nome deve conter a primeira letra maiuscula")
	private String name;
    
    @NotBlank(message ="Campo não informado")
	private String login;
    
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime updatedDate;
    
	@Email(message ="Email inválido")
    @NotBlank(message ="Campo não informado")
	private String email;
}