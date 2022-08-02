package br.com.projeto.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TB_USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String login;
	private String password;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String email;
	private String admin;
}
