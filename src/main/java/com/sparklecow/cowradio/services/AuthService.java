package com.sparklecow.cowradio.services;

import com.sparklecow.cowradio.models.dtos.user.AuthLogin;
import com.sparklecow.cowradio.models.dtos.user.AuthRequest;
import jakarta.mail.MessagingException;

public interface AuthService {
    Integer register(AuthRequest request) throws MessagingException;
    String login(AuthLogin request);
    void validateCode(String code) throws MessagingException;
}
