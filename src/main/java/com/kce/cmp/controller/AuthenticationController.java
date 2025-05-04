package com.kce.cmp.controller;

import com.kce.cmp.dto.JwtAuthResponse;
import com.kce.cmp.dto.RefreshTokenRequest;
import com.kce.cmp.dto.SignInRequest;
import com.kce.cmp.dto.SignUpRequest;
import com.kce.cmp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<JwtAuthResponse> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody String token) {
        authenticationService.logout(token);
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping
    public ResponseEntity<JwtAuthResponse> currentUser(@RequestHeader("Authorization") String token) {
        System.out.println("getting current user");
        String jwtToken = token.substring(7);
        JwtAuthResponse jwtAuthResponse = authenticationService.currentUser(jwtToken);
        return ResponseEntity.ok(jwtAuthResponse);
    }
}
