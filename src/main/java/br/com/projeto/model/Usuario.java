package br.com.projeto.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(name = "name", nullable = false)
	private String name;
    @Column(name = "login", nullable = false)
	private String login;
    @Column(name = "password", nullable = false)
	private String password;
    @Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
    @Column(name = "updated_date")
	private LocalDateTime updatedDate;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "admin", nullable = false)
	private String admin;
}
