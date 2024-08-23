package com.palmen.news.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "API NEWS",
				description = "Esta app proporciona un sistema gestor de bibliotecas",
				termsOfService = "www.palmenendez.com/terminosyservicios",
				version = "1.0.0",
				contact = @Contact(
						name = "Pablo Alonso",
						url = "www.palmenendez.com/contact",
						email = "pabloam1999@hotmail.com"
				),
				license = @License(
						name = "Standard Software Use License for palmenendez",
						url = "www.palmenendez.com/license"
				)
		),
		servers = {
				@Server(
						description = "DEV SERVER",
						url = "http://localhost:9090"
				),
				@Server(
						description = "PROD SERVER",
						url = "http://palmenendez.com:8080"
				)
		}
)
public class SwaggerConfig {

}
