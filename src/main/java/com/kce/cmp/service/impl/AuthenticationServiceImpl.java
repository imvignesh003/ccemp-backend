package com.kce.cmp.service.impl;

import com.kce.cmp.dto.JwtAuthResponse;
import com.kce.cmp.dto.RefreshTokenRequest;
import com.kce.cmp.dto.SignInRequest;
import com.kce.cmp.dto.SignUpRequest;
import com.kce.cmp.model.user.Role;
import com.kce.cmp.model.user.User;
import com.kce.cmp.repository.UserRepository;
import com.kce.cmp.service.AuthenticationService;
import com.kce.cmp.service.JWTService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public User signUp(@NonNull SignUpRequest signUpRequest) {

        User dbUser = userRepository.findByEmail(signUpRequest.getEmail()).orElse(null);
        if(dbUser != null){
            throw new IllegalArgumentException("User already exists");
        }else{
            User user = new User();
            user.setName(signUpRequest.getName());
            user.setEmail(signUpRequest.getEmail());
            user.setRole(Role.STUDENT);
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            return userRepository.save(user);
        }
    }

    @Override
    public JwtAuthResponse signIn(@NonNull SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;
    }


    @Override
    public JwtAuthResponse refreshToken(@NonNull RefreshTokenRequest refreshTokenRequest){
        String email = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(email).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            String jwtToken = jwtService.generateToken(user);
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setToken(jwtToken);
            jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthResponse;
        }
        return null;
    }
}
