package com.sparklecow.cowradio.services.user;

import com.sparklecow.cowradio.entities.user.Role;
import com.sparklecow.cowradio.models.dtos.user.AuthLogin;
import com.sparklecow.cowradio.models.dtos.user.AuthRequest;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<HttpStatus> registerUserWithRole(AuthRequest request, Role role) throws MessagingException;
    String login(AuthLogin request);
    void validateCode(String code) throws MessagingException;
}
