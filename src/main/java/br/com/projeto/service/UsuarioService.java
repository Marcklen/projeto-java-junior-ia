package br.com.projeto.service;

import java.util.List;

import br.com.projeto.model.Usuario;
import br.com.projeto.model.dto.UsuarioDTO;
import br.com.projeto.model.dto.UsuarioUpdateDTO;

public interface UsuarioService {

	// read - find all
	List<Usuario> buscarTodos();

	// read - find by id
	Usuario buscarPorId(Integer id);

	// create
	Usuario criar(UsuarioDTO dto);

	// delete - basic operation
	void deletar(Integer id);

	// update
	Usuario atualizar(Integer id, UsuarioUpdateDTO dto);
}