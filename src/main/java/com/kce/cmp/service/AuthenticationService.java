package com.kce.cmp.service;

import com.kce.cmp.dto.request.RefreshTokenRequest;
import com.kce.cmp.dto.request.SignInRequest;
import com.kce.cmp.dto.request.SignUpRequest;
import com.kce.cmp.dto.request.UpdatePasswordRequest;
import com.kce.cmp.dto.response.JwtAuthResponse;
import lombok.NonNull;

public interface AuthenticationService {
    JwtAuthResponse signUp(@NonNull SignUpRequest signUpRequest);

    JwtAuthResponse signIn(@NonNull SignInRequest signInRequest);

    JwtAuthResponse refreshToken(@NonNull RefreshTokenRequest refreshTokenRequest);

    JwtAuthResponse currentUser(@NonNull String token);

    JwtAuthResponse updatePassword(@NonNull String token, @NonNull UpdatePasswordRequest updatePasswordRequest);

    boolean updateUser(@NonNull Long id, @NonNull String name, @NonNull String profileImage);

    void logout(@NonNull String token);
}
