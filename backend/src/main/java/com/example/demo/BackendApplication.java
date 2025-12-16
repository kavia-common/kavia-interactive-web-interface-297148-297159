package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * The main entry point for the Spring Boot backend application.
 * This application exposes simple REST endpoints and includes OpenAPI configuration.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "GAI - KAVIA Backend",
                version = "0.1.0",
                description = "Backend services for GAI - KAVIA with CORS enabled and Swagger UI."
        )
)
@SpringBootApplication
public class BackendApplication {

    /**
     * PUBLIC_INTERFACE
     * Main method to launch the Spring Boot application.
     * @param args application arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
