package br.com.projeto.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.model.dto.UsuarioDTO;
import br.com.projeto.model.dto.UsuarioUpdateDTO;
import br.com.projeto.service.UsuarioService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Controller de Usuários")
public class UsuarioController {
	
	private static final String ID = "/{id}";
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = ID)
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id) {
		return ResponseEntity.
				ok().body(
						mapper.map(service.buscarPorId(id), UsuarioDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		return ResponseEntity.ok()
				.body(service.buscarTodos()
						.stream()
						.map(x -> mapper.map(x, UsuarioDTO.class))
						.collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO dto) {
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path(ID)
				.buildAndExpand(service.criar(dto).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = ID)
	public ResponseEntity<UsuarioDTO> atualizar (@PathVariable Integer id, 
			@RequestBody UsuarioUpdateDTO dto){
		buscarPorId(id);
		return ResponseEntity.ok().body(mapper.map(service.atualizar(id, dto), UsuarioDTO.class));
		
	}
	
	@DeleteMapping(value = ID)
	public ResponseEntity<UsuarioDTO> deletar (@PathVariable Integer id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}