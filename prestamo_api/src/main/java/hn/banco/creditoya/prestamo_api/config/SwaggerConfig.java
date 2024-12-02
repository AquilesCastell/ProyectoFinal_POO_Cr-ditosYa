package hn.banco.creditoya.prestamo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Sistema de Gestión de Préstamos",
        version = "1.0.0",
        description = "Proyecto de Programación Orientada a Objetos - Sección 1900",
        contact = @Contact(
            name =
                "Integrantes del Proyecto:\n" +
                "- Aquiles Castellanos (Cuenta: 20191000087)\n" +
                "- Kevin Sánchez (Cuenta: 20121003160)\n" +
                "- Gloria Suyapa Mendoza (Cuenta: 20201002242)",
            email = "poo.seccion1900@unah.hn"
        )
    )
)
public class SwaggerConfig {
}