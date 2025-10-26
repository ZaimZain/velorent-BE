package com.devion.velorent.controller;

import com.devion.velorent.dto.LoginRequestDto;
import com.devion.velorent.dto.LoginResponseDto;
import com.devion.velorent.entity.AppUser;
import com.devion.velorent.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request, HttpSession session) {
        // **Change**: Check if user is already logged in (active session)
        if (session.getAttribute("username") != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body(new LoginResponseDto(false, "Already logged in", null, null));
        }

        Optional<AppUser> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            if (request.getPassword().equals(user.getPassword())) { // Replace with password hashing check in production
                session.setAttribute("username", user);
                return ResponseEntity.ok(new LoginResponseDto(true, "Login successful", user.getUsername(), user.getRole()));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponseDto(false, "Invalid username or password", null, null));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new LoginResponseDto(true, "Logged out", null, null));
    }
}
