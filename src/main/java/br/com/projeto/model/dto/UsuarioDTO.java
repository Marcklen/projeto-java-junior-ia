package br.com.projeto.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private Integer id;

	@NotBlank(message = "Campo não informado")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "O Nome deve conter a primeira letra maiuscula")
	@Size(min = 3, max = 50, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
	private String name;

	@NotBlank(message = "Campo não informado")
	@Size(min = 3, max = 20, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
	private String login;

	@NotBlank(message = "Campo não informado")
	@Pattern(regexp = "[a-zA-Z]{3}[0-9]{5}", message = "Sua Senha deve Conter 8 digitos, sendo 3 Letras seguidas de 5 números")
	@Size(min = 3, max = 8, message = "'${validatedValue}' precisa estar entre {min} e {max} caracteres.")
	private String password;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Prencha o campo corretamente.")
	@Past(message = "Data '${validatedValue}' é inválida.")
	private LocalDate createdDate;

	@Email(message = "Email inválido")
	@NotBlank(message = "Campo não informado")
	private String email;
	
	private Boolean admin;
	
	public UsuarioDTO(int i, String string, String string2, String string3, String string4, String string5, boolean b) {
		// TODO Auto-generated constructor stub
	}
}