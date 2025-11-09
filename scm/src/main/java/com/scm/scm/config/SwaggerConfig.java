package com.scm.scm.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Contact Manager API")
                        .version("1.0")
                        .description("API documentation for Smart Contact Manager application")
                        .contact(new Contact()
                                .name("Sachin c. Kshhirsagar")
                                .email("s@chinkshir512@gmail.com"))
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://springdoc.org")))
                                        .servers(List.of(new Server().url("http://localhost:8080").description("Local server"))
                                        );

}
}
