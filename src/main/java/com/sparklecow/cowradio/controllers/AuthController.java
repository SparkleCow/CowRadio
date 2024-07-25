package com.sparklecow.cowradio.controllers;

import com.sparklecow.cowradio.models.dtos.user.AuthLogin;
import com.sparklecow.cowradio.models.dtos.user.AuthRequest;
import com.sparklecow.cowradio.models.dtos.user.AuthResponse;
import com.sparklecow.cowradio.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Controller for authentication and authorization")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody AuthRequest request) throws MessagingException {
        Integer userId = authService.register(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/users/{id}")
                .buildAndExpand(userId)
                .toUri();
        return ResponseEntity.created(uri).build();
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
