package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Database operations.
 * Spring Data JPA automatically provides methods like save(), findAll(), findById(), etc.
 */
public interface UserRepository extends JpaRepository<AppUser, Long> {
    // Custom query method: Spring automatically figures out the SQL equivalent (SELECT * FROM app_user WHERE username = ?)
    AppUser findByUsername(String username);
}
