package com.joaovictorcmd.besalonapi.security.userdetails;

import com.joaovictorcmd.besalonapi.entities.User;
import com.joaovictorcmd.besalonapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        return new UserDetailsImpl(user);
    }
}
