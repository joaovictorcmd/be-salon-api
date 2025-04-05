package com.joaovictorcmd.besalonapi.controllers;

import com.joaovictorcmd.besalonapi.dto.input.UserLoginRequestDTO;
import com.joaovictorcmd.besalonapi.dto.input.UserRegisterRequestDTO;
import com.joaovictorcmd.besalonapi.dto.output.ApiSuccessResponse;
import com.joaovictorcmd.besalonapi.dto.output.UserDTO;
import com.joaovictorcmd.besalonapi.dto.output.UserLoginResponseDTO;
import com.joaovictorcmd.besalonapi.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiSuccessResponse> register(@Valid @RequestBody UserRegisterRequestDTO data) {
        UserDTO userDTO = userService.register(data);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiSuccessResponse<>(
                                HttpStatus.CREATED.value(),
                                "User registered successfully",
                                userDTO
                        )
                );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse> login(@Valid @RequestBody UserLoginRequestDTO data) {
        UserLoginResponseDTO userLoginResponseDTO = userService.login(data);

        return ResponseEntity.ok(
                new ApiSuccessResponse<>(
                        HttpStatus.OK.value(),
                        "Login successful",
                        userLoginResponseDTO
                )
        );
    }
}
