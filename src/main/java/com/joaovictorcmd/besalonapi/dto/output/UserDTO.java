package com.joaovictorcmd.besalonapi.dto.output;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String lastName,
        String email,
        String role
) {
}
