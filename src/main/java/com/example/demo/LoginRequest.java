package com.example.demo;

import lombok.Data;

/**
 * DTO (Data Transfer Object) for login requests.
 * This class catches the JSON body sent by the user: {"username": "...", "password": "..."}
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
