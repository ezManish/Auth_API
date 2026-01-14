package com.example.demo;

import com.example.demo.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(LoginRequest request) {
        // 1. Find user in the Database
        AppUser user = userRepository.findByUsername(request.getUsername());

        // 2. Check if user exists & password matches (using BCrypt)
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        // 3. If correct, generate and return a Token
        return jwtUtil.generateToken(user.getUsername());
    }
}
