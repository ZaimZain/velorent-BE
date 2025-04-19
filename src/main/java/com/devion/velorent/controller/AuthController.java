package com.devion.velorent.controller;

import com.devion.velorent.entity.Role;
import com.devion.velorent.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/hello")
    public String login(){
        return "Hello";
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}

@Data
@AllArgsConstructor
class AuthResponse {
    private String token;
    private Role role;
}
