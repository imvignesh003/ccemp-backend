package com.kce.cmp.service;

import com.kce.cmp.dto.JwtAuthResponse;
import com.kce.cmp.dto.RefreshTokenRequest;
import com.kce.cmp.dto.SignInRequest;
import com.kce.cmp.dto.SignUpRequest;
import com.kce.cmp.model.user.User;
import lombok.NonNull;

public interface AuthenticationService {
    User signUp(@NonNull SignUpRequest signUpRequest);

    JwtAuthResponse signIn(@NonNull SignInRequest signInRequest);

    JwtAuthResponse refreshToken(@NonNull RefreshTokenRequest refreshTokenRequest);
}
