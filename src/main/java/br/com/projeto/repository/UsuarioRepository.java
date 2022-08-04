package br.com.projeto.repository;

import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.model.Usuario;
//import br.com.projeto.model.dto.UsuarioDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByLogin(String login);

//	UsuarioDTO save(@Valid UsuarioDTO usuario);

}
