package com.emc.sgcc_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Configuración de OpenAPI (Swagger) para la API SGCC.
 * Define información general, servidores, esquemas de seguridad y más.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SGCC API")
                        .version("1.0.0")
                        .description("API para la gestión de suministros y consumo de servicios (agua, electricidad, gas). " +
                                "Permite administrar ubicaciones, medidores, lecturas, facturación y asignaciones de consumo.")
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(Arrays.asList(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo"),
                        new Server()
                                .url("https://api.sgcc.prod")
                                .description("Servidor de producción")))
                .addSecurityItem(new SecurityRequirement().addList("ApiKey"))
                .addSecurityItem(new SecurityRequirement().addList("SystemId"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer JWT"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("ApiKey", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("X-API-Key")
                                .description("API Key para acceso a la API"))
                        .addSecuritySchemes("SystemId", new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name("X-System-Id")
                                .description("ID del sistema cliente"))
                        .addSecuritySchemes("Bearer JWT", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT Bearer Token para autenticación de usuarios")));
    }
}
