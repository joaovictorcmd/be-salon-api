package com.joaovictorcmd.besalonapi.repositories;

import com.joaovictorcmd.besalonapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
