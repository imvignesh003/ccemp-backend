package com.kce.cmp.repository;

import com.kce.cmp.model.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByToken(String token);
    void deleteByToken(String token);
    boolean existsByToken(String token);
}
