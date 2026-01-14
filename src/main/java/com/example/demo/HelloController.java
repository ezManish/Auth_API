package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Test Controller to verify if JWTs are working.
 * This endpoint is PROTECTED. You can only call it with a valid token.
 */
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, you are authenticated with JWT!";
    }
}
