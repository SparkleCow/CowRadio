package com.sparklecow.cowradio.models.dtos.user;

import java.time.LocalDate;

public record AuthRequest(
        String firstName,
        String lastName,
        String email,
        String username,
        String password,
        LocalDate dateOfBirth
) {
}
