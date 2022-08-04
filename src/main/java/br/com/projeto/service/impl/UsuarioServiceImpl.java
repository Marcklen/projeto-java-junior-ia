package br.com.projeto.service.impl;

import java.util.List;
import java.util.Optional;

//import javax.transaction.Transactional;
//import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.exception.UsuarioNaoEncontradoException;
import br.com.projeto.model.Usuario;
import br.com.projeto.model.dto.UsuarioDTO;
import br.com.projeto.model.dto.UsuarioUpdateDTO;
import br.com.projeto.repository.UsuarioRepository;
import br.com.projeto.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{ //,UserDetailsService{

//	@Autowired
//	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}
	
	@Override
	public Usuario criar(UsuarioDTO dto) {
		// TODO Auto-generated method stub
		findByLogin(dto);
		return usuarioRepository.save(mapper.map(dto, Usuario.class));
	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub
		buscarPorId(id);
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario atualizar(Integer id, UsuarioUpdateDTO dto) {
		// TODO Auto-generated method stub
		Usuario usuario = buscarPorId(id);
		usuario.setLogin(dto.getLogin());
		usuario.setName(dto.getName());
		usuario.setEmail(dto.getEmail());
		usuario.setUpdatedDate(dto.getUpdatedDate());
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	//metodo para saber se ja existe um login de usuario igual e que também será usado para carregar o usuario ADMIN ou USER
	private void findByLogin(UsuarioDTO dto) {
		Optional<Usuario> user = usuarioRepository.findByLogin(dto.getLogin());
		if(user.isPresent() && !user.get().getId().equals(dto.getId())) { 
			throw new DataIntegrityViolationException("Login já cadastrado em nosso sistema!");
		}
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		Usuario usuario = usuarioRepository.findByLogin(username)
//			.orElseThrow(() -> 
//				new UsernameNotFoundException("Usuário nao encontrando no sistema !"));
//	
//		String[] roles = usuario.isAdmin() ?
//				new String[] {"ADMIN", "USER"} : new String[] {"USER"};
//		
//		return User
//				.builder()
//				.username(usuario.getLogin())
//				.password(usuario.getPassword())
//				.roles(roles)
//				.build();
//	}
	
//	@Transactional
//	public Usuario logar(UsuarioDTO usuario) {
//		return usuarioRepository.save(usuario);
//	}
//	@Transactional
//	public UsuarioDTO logar(@Valid UsuarioDTO usuario) {
//		// TODO Auto-generated method stub
//		return usuarioRepository.save(usuario);
//	}
}