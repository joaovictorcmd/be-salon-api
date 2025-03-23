package com.joaovictorcmd.besalonapi.dto.output;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
public record UserLoginResponseDTO(
        String accessToken,
        String tokenType
) {
}
