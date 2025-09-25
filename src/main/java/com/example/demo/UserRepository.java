package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<app_user, Long> {
    app_user findByUsername(String username);
}
