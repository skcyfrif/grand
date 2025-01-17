package com.cyfrifpro.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginCredentials {
    private String email;
    private String password;

    // Getters and Setters
}
