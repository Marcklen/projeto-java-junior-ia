package br.com.projeto.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Controller de Usuários")
public class UsuarioController {

	@RequestMapping(value = "/ola/{nome}", 
			method = RequestMethod.GET, 
			consumes = { "application/json","application/xml" })
	@ResponseBody
	public String helloPeople(@PathVariable("nome") String nome) {
		return String.format("Hello %s", nome);
	}
}
