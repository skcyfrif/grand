package com.cyfrifpro.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admin", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phone;
    
    @Column(nullable = true, length = 100)
	private String role;  // ADMIN

    // Additional fields if needed
}
