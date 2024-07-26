package com.sparklecow.cowradio.controllers;

import com.sparklecow.cowradio.entities.user.Role;
import com.sparklecow.cowradio.models.dtos.user.AuthLogin;
import com.sparklecow.cowradio.models.dtos.user.AuthRequest;
import com.sparklecow.cowradio.models.dtos.user.AuthResponse;
import com.sparklecow.cowradio.services.user.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Controller for authentication and authorization")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody AuthRequest request) throws MessagingException {
        return authService.registerUserWithRole(request, Role.USER);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<HttpStatus> registerAdmin(@RequestBody AuthRequest request) throws MessagingException {
        return authService.registerUserWithRole(request, Role.ADMIN);
    }

    @PostMapping("/register/artist")
    public ResponseEntity<HttpStatus> registerArtist(@RequestBody AuthRequest request) throws MessagingException {
        return authService.registerUserWithRole(request, Role.ARTIST);
    }

    @PostMapping("/register/vip")
    public ResponseEntity<HttpStatus> registerUserVIP(@RequestBody AuthRequest request) throws MessagingException {
        return authService.registerUserWithRole(request, Role.USER_VIP);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLogin request) {
        return ResponseEntity.ok(new AuthResponse(authService.login(request)));
    }

    @GetMapping("/activate")
    public ResponseEntity<HttpStatus> activate(@RequestParam(name = "code", required = true) String code) throws MessagingException {
        authService.validateCode(code);
        return ResponseEntity.ok().build();
    }
}
