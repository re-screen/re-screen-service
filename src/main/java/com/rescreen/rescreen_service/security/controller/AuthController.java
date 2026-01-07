package com.rescreen.rescreen_service.security.controller;

import com.rescreen.rescreen_service.security.dto.LoginRequest;
import com.rescreen.rescreen_service.security.dto.LoginResponse;
import com.rescreen.rescreen_service.security.jwt.JwtService;
import com.rescreen.rescreen_service.security.model.User;
import com.rescreen.rescreen_service.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found in DB"));

        System.out.println("DB password: " + user.getPassword());
        System.out.println("Request password: " + request.getPassword());

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("Password matches? " + matches);

        if (!matches) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(new LoginResponse(token));
    }

}
