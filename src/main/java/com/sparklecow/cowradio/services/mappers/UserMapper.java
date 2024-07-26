package com.sparklecow.cowradio.services.mappers;

import com.sparklecow.cowradio.models.dtos.user.AuthRequest;
import com.sparklecow.cowradio.entities.user.Role;
import com.sparklecow.cowradio.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public User toUser(AuthRequest user, Role role) {
        return User.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .username(user.username())
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .dateOfBirth(user.dateOfBirth())
                .roles(Set.of(role))
                .build();
    }
}
