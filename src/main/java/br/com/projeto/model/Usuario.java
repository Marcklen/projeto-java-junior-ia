package br.com.projeto.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "login", unique = true, nullable = false)
	@NotEmpty(message = "campo LOGIN é obrigatorio")
	private String login;
	
	@Column(name = "password", nullable = false)
	@NotEmpty(message = "campo PASSWORD é obrigatorio")
	private String password;
	
	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;
	
	@Column(name = "updated_date")
	private LocalDate updatedDate;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "admin", nullable = false)
	private Boolean admin;

}
