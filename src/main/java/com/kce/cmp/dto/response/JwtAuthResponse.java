package com.kce.cmp.dto.response;

import com.kce.cmp.model.user.User;
import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private String refreshToken;
    private User user;
}
