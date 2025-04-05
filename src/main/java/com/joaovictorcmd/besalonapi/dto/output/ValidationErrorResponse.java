package com.joaovictorcmd.besalonapi.dto.output;

import java.util.List;


public record ValidationErrorResponse(
        ApiErrorResponse apiErrorResponse,
        List<FieldMessage> errors
) {
}
