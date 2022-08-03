package br.com.projeto.service.impl;

import java.util.List;

import br.com.projeto.exception.UsuarioNaoEncontradoException;
import br.com.projeto.model.Usuario;
import br.com.projeto.repository.UsuarioRepository;
import br.com.projeto.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	private final UsuarioRepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}	
	
	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id)
				.orElseThrow(() -> 
				new UsuarioNaoEncontradoException(id));
	}
	
	@Override
	public Usuario criar(Usuario novoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub
		buscarPorId(id);
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario atualizar(Integer id, Usuario atualizarUsuario) {
		// TODO Auto-generated method stub
		Usuario usuario = buscarPorId(id);
		usuario.setLogin(atualizarUsuario.getLogin());
		usuario.setName(atualizarUsuario.getName());
		usuario.setEmail(atualizarUsuario.getEmail());
		usuarioRepository.save(usuario);
		return usuario;
	}
}
