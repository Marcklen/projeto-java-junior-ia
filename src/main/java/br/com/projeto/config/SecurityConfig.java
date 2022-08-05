package br.com.projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.projeto.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private UsuarioServiceImpl usuarioService;
//
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	//autencicação e informação de onde vem os usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(usuarioService)
//			.passwordEncoder(passwordEncoder());
		
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("marcklen")
			.password(passwordEncoder().encode("123"))
			.roles("ADMIN");
			
	}
	
	//autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // desabilitado pois é uma API REST Stateless
			.authorizeRequests()
				.antMatchers("/clientes/**")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/clientes/logar**")
					.hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/cliente/logar**")
					.permitAll().anyRequest().authenticated()
				.and()
					.httpBasic();
	}
}