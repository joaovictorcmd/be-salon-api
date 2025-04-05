package com.joaovictorcmd.besalonapi.dto.output;

import java.time.Instant;

public record ApiSuccessResponse<T>(
        Instant timestamp,
        int status,
        String message,
        T data
) {
    public ApiSuccessResponse(int status, String message, T data) {
        this(Instant.now(), status, message, data);
    }
}
