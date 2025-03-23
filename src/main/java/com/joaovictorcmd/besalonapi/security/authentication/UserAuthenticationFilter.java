package com.joaovictorcmd.besalonapi.security.authentication;

import com.joaovictorcmd.besalonapi.entities.User;
import com.joaovictorcmd.besalonapi.repositories.UserRepository;
import com.joaovictorcmd.besalonapi.security.configuration.SecurityConfiguration;
import com.joaovictorcmd.besalonapi.security.userdetails.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!isEndpointPublic(request)) {
            String token = recoveryToken(request);

            if (token != null) {
                String subject = jwtTokenService.getSubjectFromToken(token);

                User user = userRepository.findByEmail(subject).orElseThrow(
                        () -> new UsernameNotFoundException("User " + subject + " not found")
                );

                UserDetailsImpl userDetails = new UserDetailsImpl(user);

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isEndpointPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        for (String endpoint : SecurityConfiguration.PUBLIC_ENDPOINTS) {
            if (requestURI.equals(endpoint) || requestURI.startsWith(endpoint + "/")) {
                return true;
            }
        }
        return false;
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
