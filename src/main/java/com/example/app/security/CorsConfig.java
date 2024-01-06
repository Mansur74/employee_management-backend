package com.example.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Specify the endpoint(s) you want to enable CORS for
            .allowedOrigins("http://localhost:3000") // Allow requests from this origin
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Specify the HTTP methods allowed
            .allowCredentials(true); // If you need to support cookies or authorization headers
    }
}