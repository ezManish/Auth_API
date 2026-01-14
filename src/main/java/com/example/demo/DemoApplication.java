package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;  // ✅ Import added

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("student1") == null) {
                AppUser user = new AppUser();
                user.setUsername("student1");
                user.setPassword(passwordEncoder.encode("pass123")); // ✅ encoded password
                userRepository.save(user);
                System.out.println("Sample user created: student1 / pass123");
            }
        };
    }
}
