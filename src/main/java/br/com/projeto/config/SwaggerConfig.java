package br.com.projeto.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.projeto"))
				.build()
				.apiInfo(apiInfo())
				.securityContexts(Arrays.asList(actuatorSecurityContext()))
				.securitySchemes(Arrays.asList(basicAuthScheme()));

	}

	private SecurityContext actuatorSecurityContext() {
		return SecurityContext
				.builder()
				.securityReferences(Arrays.asList(
						basicAuthReference())).build();
	}

	private SecurityScheme basicAuthScheme() {
		return new BasicAuth("basicAuth");
	}

	private SecurityReference basicAuthReference() {
		return new SecurityReference("basicAuth", 
				new AuthorizationScope[0]);
	}

	private List<SecurityScheme> basicScheme() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new BasicAuth(
				"basicAuth"));
		return schemeList;
	}

	private ApiKey apiKey() {
		return new ApiKey("apiKey", 
				"Authorization", 
				"header");
	}

	private ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("SOLUCAO TESTE JUNIOR IA")
				.description("Spring Boot and Spring Cloud Rest Api para Teste Desenvolvedor")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}
}