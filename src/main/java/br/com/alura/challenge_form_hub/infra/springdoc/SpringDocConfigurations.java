package com.aluraone.forumHub.infra.springdoc;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    //metodo para poder agregar el token a los metodos de los controllers que precisen

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                       /* .info(new Info()
                            .title("ForumHub API")
                            .description("API Rest da aplicação ForumHub, contendo as funcionalidades de CRUD de tópicos e cursos")
                            .contact(new Contact()
                                  .name("Meybis Cruz Rodriguez")
                                  .email("mcruzrodriguez1985@gmil.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forumhub/api/licenca")))*/;
    }


}
