package org.example.doandemo3.controller;

import lombok.RequiredArgsConstructor;
import org.example.doandemo3.dto.LoginRequest;
import org.example.doandemo3.dto.LoginResponse;
import org.example.doandemo3.dto.RegisterRequest;
import org.example.doandemo3.dto.RegisterResponse;
import org.example.doandemo3.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    public ResponseEntity<RegisterResponse> verifyAccount(@RequestParam("token") String token) {
        RegisterResponse response = authService.verifyAccount(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
