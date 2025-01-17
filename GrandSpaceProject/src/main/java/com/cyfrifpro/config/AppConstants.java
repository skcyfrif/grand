package com.cyfrifpro.config;

import java.util.Collections;
import java.util.Map;

public class AppConstants {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 hours validity for JWT token

    // Public URLs accessible without authentication
    public static final String[] PUBLIC_URLS = { "/auth/**", "/register/**", "/login/**" , "/**" };  // Public URLs

    // Role-based URL access control (role-based restrictions on URLs)
    public static final Map<String, String[]> ROLE_URLS = Collections.unmodifiableMap(Map.of(
            "ADMIN", new String[]{"/admin/**"},
            "MANAGER", new String[]{"/control-panel/**"},
            "CLIENT", new String[]{"/control-panel/**"}
    ));
}
