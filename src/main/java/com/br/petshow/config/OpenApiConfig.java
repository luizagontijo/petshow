package com.br.petshow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PetShow API",
                version = "1.0.0",
                description = "API para cadastro e consulta de animais do PetShow"
               // contact = @Contact(name = "Time API", email = "api@petshow.com.br"),
               // license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Desenvolvimento")
                // @Server(url = "https://api.petshow.com.br", description = "Produção")
        },
        externalDocs = @ExternalDocumentation(
                description = "Repositório do projeto",
                url = "https://github.com/luizagontijo/petshow"
        )
)
public class OpenApiConfig { }

