package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global CORS (Cross-Origin Resource Sharing) Configuration.
 * This ensures that other websites (local or production) can talk to this API.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // "/**" means apply to ALL endpoints in the application
                registry.addMapping("/**")
                        .allowedOrigins("*") //Allows ALL domains. Good for dev. In Prod, replace with "https://your-domain.com"
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
