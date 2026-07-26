package com.devion.velorent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${app.auth.enabled:true}")
    private boolean authEnabled;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable);
        
        // DEVELOPMENT ONLY    
        if (!authEnabled) {
            http
                .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
                )
                .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        } else {
            http
                .authorizeHttpRequests(auth -> auth

                    // Login/logout/refresh token
                    .requestMatchers("/api/auth/**").permitAll()

                    // Admin only
                    .requestMatchers("/api/admin/**")
                    .hasRole("ADMIN")

                    // Agent and Admin
                    .requestMatchers("/api/cars/**")
                    .hasAnyRole("ADMIN", "AGENT")

                    .requestMatchers("/api/customers/**")
                    .hasAnyRole("ADMIN", "AGENT")

                    .requestMatchers("/api/rentals/**")
                    .hasAnyRole("ADMIN", "AGENT")

                    // Everything else requires login
                    .anyRequest()
                    .authenticated()
                )
                .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );
        }
        http
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }
}