package com.joaovictorcmd.besalonapi.entities;

import com.joaovictorcmd.besalonapi.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author joaovictorcmd
 * @date 2025 Mar 21
 */
@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String lastName;

    @Column(unique = true)
    private String phoneNumber;

    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
