package com.joaovictorcmd.besalonapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@AllArgsConstructor
@Getter
public enum UserRole {

    ADMIN("ROLE_ADMIN"),
    EMPLOYEE("ROLE_EMPLOYEE"),
    CUSTOMER("ROLE_CUSTOMER");

    private final String role;
}
