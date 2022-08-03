package br.com.projeto.service;

import java.util.List;

import br.com.projeto.model.Usuario;

public interface UsuarioService {

	// read - find all
	List<Usuario> buscarTodos();

	// read - find by id
	Usuario buscarPorId(Integer id);

	// create
	Usuario criar(Usuario novoUsuario);

	// delete - basic operation
	void deletar(Integer id);
	
	// update
	Usuario atualizar(Integer id, Usuario atualizarUsuario);
}
