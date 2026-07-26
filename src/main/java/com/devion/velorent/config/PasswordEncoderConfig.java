package com.devion.velorent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Value("${app.password.encrypted:true}")
    private boolean passwordEncrypted;

    @Bean
    public PasswordEncoder passwordEncoder() {

        if (passwordEncrypted) {
            // PROD:
            // Passwords stored in DB must be BCrypt hashed
            return new BCryptPasswordEncoder();
        } else {
            // DEVELOPMENT ONLY:
            // Allows plain password comparison for troubleshooting
            // REMOVE BEFORE PRODUCTION
            return NoOpPasswordEncoder.getInstance();
        }
    }
}