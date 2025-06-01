package com.example.olibrary.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Login")
@AllArgsConstructor
public class AuthRequest {
    @Schema(description = "username", example = "username")
    private String username;
    @Schema(description = "password", example = "password")
    private String password;
}