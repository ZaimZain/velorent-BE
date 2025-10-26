package com.devion.velorent.dto;

import com.devion.velorent.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class LoginResponseDto {
    private boolean success;
    private String message;
    private String username;
    private Role role;
}
