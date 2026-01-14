package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Represents a User in the database.
 * The @Entity annotation tells Hibernate to make a table out of this class.
 */
@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key (Auto-incremented)

    private String username; // Unique username
    private String password; // BCrypt Hashed password (NOT plain text)
}
