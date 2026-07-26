package com.devion.velorent.controller;

import com.devion.velorent.dto.LoginRequestDto;
import com.devion.velorent.dto.LoginResponseDto;
import com.devion.velorent.entity.AppUser;
import com.devion.velorent.repository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            if (passwordEncoder.matches(
                    request.getPassword(),
                    user.getPassword()
            )) {

                // Store user for our application logic
                session.setAttribute("username", user);


                // Tell Spring Security this user is authenticated
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                null,
                                List.of(
                                    new SimpleGrantedAuthority(
                                        "ROLE_" + user.getRole()
                                    )
                                )
                        );

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                session.setAttribute("SPRING_SECURITY_CONTEXT",context);

                return ResponseEntity.ok(
                    new LoginResponseDto(
                        true,
                        "Login successful",
                        user.getUsername(),
                        user.getRole()
                    )
                );
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new LoginResponseDto(false, "Invalid username or password", null, null));
    }

   @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session,HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok(
            new LoginResponseDto(true, "Logged out", null, null)
        );
    }
}
