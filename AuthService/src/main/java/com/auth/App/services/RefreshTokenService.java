package com.auth.App.services;

import com.auth.App.entities.RefreshToken;
import com.auth.App.entities.UserInfo;
import com.auth.App.repository.RefreshTokenRepository;
import com.auth.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userInfoRepository;

    public RefreshToken createRefreshToken(String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        RefreshToken refreshToken = RefreshToken.builder()
                                                .user(userInfo)
                                                .token(UUID.randomUUID().toString()) // Assume generateToken() is a method that generates a unique token
                                                .expiresAt(Instant.now().plusMillis(600000)) // Assume calculateExpiryDate() sets the expiry date
                                                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiresAt().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
