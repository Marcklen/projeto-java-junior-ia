package br.com.projeto.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.projeto.exception.UsuarioNaoEncontradoException;
import br.com.projeto.model.Usuario;
import br.com.projeto.model.dto.UsuarioDTO;
import br.com.projeto.model.dto.UsuarioUpdateDTO;
import br.com.projeto.repository.UsuarioRepository;

@SpringBootTest
class UsuarioServiceImplTest {

	private static final boolean ROLE 		 = true; // aqui sera para saber se é ou nao ADMIN
	private static final String EMAIL		 = "marcklen@email.com";
	private static final LocalDate CREATED_DATE = LocalDate.of(2020, 10, 01);
	private static final LocalDate UPDATED_DATE = LocalDate.of(2020, 10, 01);
	private static final String PASSWORD 	 = "mmg13579";
	private static final String LOGIN 		 = "marcklenmg";
	private static final String NAME    	 = "Marcklen";
	private static final Integer ID			 = 1;

	@InjectMocks // criando uma instancia real do usarioserviceimpl os demais serão apenas mocks
	private UsuarioServiceImpl service;

	@Mock // tem que ter essa instancia mocada , caso contrario vai dar erro
			// NullPointerException
	private UsuarioRepository repository;
	@Mock
	private ModelMapper mapper;

	private Usuario usuario;
	private UsuarioDTO usuarioDTO;
//	private UsuarioUpdateDTO usuarioUpdateDTO;
	private Optional<Usuario> optionalUsuario;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this); // fazendo referencia a essa classe que estamos testando
		startUsuario();
	}

	@Test //when find all the return success 
	void testBuscarTodos() {
		when(repository.findAll()).thenReturn(List.of(usuario));
		
		List<Usuario> response = service.buscarTodos();
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Usuario.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NAME, response.get(0).getName());
		assertEquals(LOGIN, response.get(0).getLogin());
		assertEquals(PASSWORD, response.get(0).getPassword());
		
	}

	@Test //when find by id the return a object 
	void testBuscarPorId() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUsuario);
		
		Usuario response = service.buscarPorId(ID);
	
		//assegure que o objeto nao sera nulo
		assertNotNull(response);
		//assegure que ambos sao iguais
		assertEquals(Usuario.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(LOGIN, response.getLogin());
		assertEquals(PASSWORD, response.getPassword());
	}
	
	//creating a test to verify the UsuarioNotFoundExcp. 
	@Test
	void whenBuscarPorIdThenReturnAnUsuarioNotFoundException() {
		when(repository.findById(Mockito.anyInt())).thenThrow(
				new UsuarioNaoEncontradoException(ID));
		try {
			service.buscarPorId(ID);
		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(UsuarioNaoEncontradoException.class, e.getClass());
		}
	}

	@Test // when create something then return success
	void testCriar() {
		when(repository.save(any())).thenReturn(usuario);
		
		Usuario response = service.criar(usuarioDTO);
		
		assertNotNull(response);
		assertEquals(Usuario.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(LOGIN, response.getLogin());
		assertEquals(PASSWORD, response.getPassword());
	}

	@Test // checking an object find by login
	void whenCriarTheReturnAnDataIntegrityViolationException() {
		when(repository.findByLogin(anyString())).thenReturn(optionalUsuario);
		
		try {
			optionalUsuario.get().setId(2);
			service.criar(usuarioDTO);		
		} catch (Exception e) {
			// TODO: handle exception
			assertEquals(DataIntegrityViolationException.class, e.getClass());
			assertEquals("Login já cadastrado em nosso sistema!", e.getMessage());
			
		}
	}	
	
	@Test
	void testDeletarWithSuccess() {
		when(repository.findById(ID)).thenReturn(optionalUsuario);
		doNothing().when(repository).deleteById(anyInt());
		service.deletar(ID);
		
		verify(repository, times(1)).deleteById(anyInt());
	}

	
//	@Test //when update object the return success
//	void testAtualizarThenReturnSuccess() {
//		when(repository.save(any())).thenReturn(usuario);
//		
//		Usuario response = service.atualizar(ID, usuarioUpdateDTO);
//		
//		assertNotNull(response);
//		assertEquals(Usuario.class, response.getClass());
//		assertEquals(ID, response.getId());
//		assertEquals(NAME, response.getName());
//		assertEquals(LOGIN, response.getLogin());
//	}

	private void startUsuario() {
		usuario = new Usuario(ID, NAME, LOGIN, PASSWORD, CREATED_DATE, EMAIL, ROLE);
		usuarioDTO = new UsuarioDTO(ID, NAME, LOGIN, PASSWORD, CREATED_DATE, EMAIL, ROLE);
//		usuarioUpdateDTO = new UsuarioUpdateDTO(LOGIN, NAME, EMAIL, UPDATED_DATE);
		optionalUsuario = Optional.of(new Usuario(ID, NAME, LOGIN, PASSWORD, CREATED_DATE, EMAIL, ROLE));
	}
}