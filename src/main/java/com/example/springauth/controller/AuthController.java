package com.example.springauth.controller;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.springauth.jwt.JWTAuthResponse;
import com.example.springauth.model.LoginDto;
import com.example.springauth.model.SignupDto;
import com.example.springauth.service.AuthService;
import com.example.springauth.service.AuthService.AuthServiceLogin;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthServiceLogin authServiceLogin;
    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        String token = authServiceLogin.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupDto signUpRequest) {
        authService.signup(signUpRequest);
        return ResponseEntity.ok().body("User registered successfully!");
    }

}