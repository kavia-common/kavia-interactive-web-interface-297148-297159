package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * PUBLIC_INTERFACE
 * CORS configuration for the backend.
 * It reads allowed origins from environment variables and sets sensible defaults for development.
 *
 * Environment variables to be provided via container orchestration:
 * - REACT_APP_FRONTEND_URL: the external URL of the frontend application (e.g., https://example.com)
 * - REACT_APP_API_BASE or REACT_APP_BACKEND_URL: optional backend base URL (useful for self-origin)
 *
 * Note: Do not hardcode values here. Provide them via .env as per the project orchestration.
 */
@Configuration
public class CorsConfig {

    @Value("${REACT_APP_FRONTEND_URL:}")
    private String frontendUrl;

    @Value("${REACT_APP_BACKEND_URL:}")
    private String backendUrl;

    @Value("${REACT_APP_API_BASE:}")
    private String apiBaseUrl;

    /**
     * PUBLIC_INTERFACE
     * Registers a global CORS filter with allowed origins and headers suitable for SPA + API development.
     * @return CorsFilter bean
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials for cookie-based auth if needed later; safe during dev
        config.setAllowCredentials(true);

        // Build allowed origins from provided envs, plus common localhost dev ports
        // This keeps it flexible while enabling secure production configuration from env
        if (isNonEmpty(frontendUrl)) {
            config.addAllowedOriginPattern(frontendUrl);
        }
        if (isNonEmpty(backendUrl)) {
            config.addAllowedOriginPattern(backendUrl);
        }
        if (isNonEmpty(apiBaseUrl)) {
            config.addAllowedOriginPattern(apiBaseUrl);
        }

        // Localhost/dev ports (common during development)
        for (String local : Arrays.asList(
                "http://localhost:*",
                "http://127.0.0.1:*",
                "https://localhost:*",
                "https://127.0.0.1:*"
        )) {
            config.addAllowedOriginPattern(local);
        }

        // Allow common headers and methods
        config.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Cache-Control",
                "Content-Type",
                "X-Requested-With",
                "X-CSRF-Token",
                "Accept",
                "Origin"
        ));

        config.setExposedHeaders(Arrays.asList(
                "Authorization",
                "Content-Disposition",
                "Content-Type"
        ));

        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));

        // Apply to all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    private boolean isNonEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
