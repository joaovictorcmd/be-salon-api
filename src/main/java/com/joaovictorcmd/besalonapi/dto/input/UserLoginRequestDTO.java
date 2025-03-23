package com.joaovictorcmd.besalonapi.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
public record UserLoginRequestDTO(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
