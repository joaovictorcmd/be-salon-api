package com.joaovictorcmd.besalonapi.services;

import com.joaovictorcmd.besalonapi.dto.input.UserLoginRequestDTO;
import com.joaovictorcmd.besalonapi.dto.input.UserRegisterRequestDTO;
import com.joaovictorcmd.besalonapi.dto.output.UserDTO;
import com.joaovictorcmd.besalonapi.dto.output.UserLoginResponseDTO;
import com.joaovictorcmd.besalonapi.entities.User;
import com.joaovictorcmd.besalonapi.enums.UserRole;
import com.joaovictorcmd.besalonapi.exceptions.EmailAlreadyExistsException;
import com.joaovictorcmd.besalonapi.repositories.UserRepository;
import com.joaovictorcmd.besalonapi.security.authentication.JwtTokenService;
import com.joaovictorcmd.besalonapi.security.configuration.SecurityConfiguration;
import com.joaovictorcmd.besalonapi.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;

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

    public UserDTO register(UserRegisterRequestDTO data) {

        if (userRepository.findByEmail(data.email()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = User.builder()
                .email(data.email())
                .password(securityConfiguration.passwordEncoder().encode(data.password()))
                .name(data.name())
                .lastName(data.lastName())
                .phoneNumber(data.phoneNumber())
                .birthDate(data.birthDate())
                .role(UserRole.valueOf(data.role()))
                .build();

        userRepository.save(user);

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                formatRole(user.getRole())
        );
    }

    public UserLoginResponseDTO login(UserLoginRequestDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            data.email(), data.password()
                    );

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenService.generateToken((UserDetailsImpl) userDetails);
            Instant expiration = jwtTokenService.expirationDate();

            UserDTO userDTO = new UserDTO(
                    ((UserDetailsImpl) userDetails).getUser().getId(),
                    ((UserDetailsImpl) userDetails).getUser().getName(),
                    ((UserDetailsImpl) userDetails).getUser().getLastName(),
                    userDetails.getUsername(),
                    formatRole(((UserDetailsImpl) userDetails).getUser().getRole())
            );

            return new UserLoginResponseDTO(token, expiration, userDTO);
        } catch (Exception exception) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }

    private String formatRole(UserRole role) {
        return role.name().replace("ROLE_", "");
    }
}
