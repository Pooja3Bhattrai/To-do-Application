package com.Todo_Application.Todo_Application.config;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {

//    /**
//     * Configures and initializes OpenAPI documentation for the Task Management API
//     *
//     * @Bean marks this method as a source of bean definitions
//     * @return OpenAPI object configured with API information and security schemes
//     *
//     * This configuration:
//     * 1. Sets basic API information (title, version, description)
//     * 2. Configures basic authentication security
//     * 3. Adds security requirement for all endpoints
//     */
    @Bean
    public OpenAPI openAPI() {
        return (new OpenAPI()).info((new Info()).title("Task Management API").version("1.0")
                .description("API for managing tasks")).addSecurityItem((new SecurityRequirement())
                .addList("basicAuth")).components((new Components()).addSecuritySchemes("basicAuth", (new SecurityScheme()).type(SecurityScheme.Type.HTTP).scheme("basic")));
    }

}
