package com.kce.cmp.model.auth;

import com.kce.cmp.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private boolean revoked = false;

    @ManyToOne
    private User user;

    private LocalDateTime expiryDate;


    public RefreshToken(String refreshToken, User user) {
        this.token = refreshToken;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusDays(7);
    }
}
