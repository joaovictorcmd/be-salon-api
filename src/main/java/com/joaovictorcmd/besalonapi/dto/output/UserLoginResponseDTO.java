package com.joaovictorcmd.besalonapi.dto.output;


import java.time.Instant;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
public record UserLoginResponseDTO(
        String accessToken,
        Instant expiration,
        UserDTO userDTO
) {
}
