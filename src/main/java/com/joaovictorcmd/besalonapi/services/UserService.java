package com.joaovictorcmd.besalonapi.services;

import com.joaovictorcmd.besalonapi.dto.input.UserLoginRequestDTO;
import com.joaovictorcmd.besalonapi.dto.input.UserRegisterRequestDTO;
import com.joaovictorcmd.besalonapi.dto.output.UserLoginResponseDTO;
import com.joaovictorcmd.besalonapi.entities.User;
import com.joaovictorcmd.besalonapi.enums.UserRole;
import com.joaovictorcmd.besalonapi.repositories.UserRepository;
import com.joaovictorcmd.besalonapi.security.authentication.JwtTokenService;
import com.joaovictorcmd.besalonapi.security.configuration.SecurityConfiguration;
import com.joaovictorcmd.besalonapi.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final SecurityConfiguration securityConfiguration;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public UserLoginResponseDTO login(UserLoginRequestDTO data) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        data.email(), data.password()
                );

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new UserLoginResponseDTO(jwtTokenService.generateToken((UserDetailsImpl) userDetails), "Bearer");
    }

    public void register(UserRegisterRequestDTO data) {
        User newUser = User.builder()
                .email(data.email())
                .password(securityConfiguration.passwordEncoder().encode(data.password()))
                .name(data.name())
                .lastName(data.lastName())
                .phoneNumber(data.phoneNumber())
                .birthDate(data.birthDate())
                .role(UserRole.valueOf(data.role()))
                .build();

        userRepository.save(newUser);
    }

}
