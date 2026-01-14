package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main Security Configuration.
 * This class defines "who can access what" and "how to check logins".
 */
@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Defines the Security Filter Chain.
     * This method acts as the "Bouncer" for the application.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs (not needed for stateless JWT)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll() // Allow everyone to login
                        .anyRequest().authenticated() // All other requests need a token
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil), // Add our custom JWT filter
                        UsernamePasswordAuthenticationFilter.class // ...before the standard password check
                )
                .httpBasic(basic -> {}); // Optional: allow basic auth for testing

        return http.build();
    }

    /**
     * Provides the PasswordEncoder (BCrypt).
     * This component allows us to hash passwords (safely) and verify them.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
