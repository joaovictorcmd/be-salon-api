package com.joaovictorcmd.besalonapi.controllers;

import com.joaovictorcmd.besalonapi.dto.input.UserLoginRequestDTO;
import com.joaovictorcmd.besalonapi.dto.input.UserRegisterRequestDTO;
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

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginRequestDTO data) {
        UserLoginResponseDTO userLoginResponseDTO = userService.login(data);

        return ResponseEntity.ok(userLoginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody UserRegisterRequestDTO data) {
        userService.register(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
