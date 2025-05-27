package com.ime.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // ✅ Restreindre aux routes /api
                      .allowedOrigins("http://localhost:3000")
                      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                      .allowedHeaders("*")
                      .exposedHeaders("Authorization") // ✅ Important pour JWT
                      .allowCredentials(true) // ✅ Nécessaire pour les cookies/auth
                      .maxAge(3600);
            }
        };
    }
}