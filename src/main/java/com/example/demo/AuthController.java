package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Authentication endpoints.
 * All login-related requests come here first.
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/login: Receives {username, password} -> Returns JWT Token
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
