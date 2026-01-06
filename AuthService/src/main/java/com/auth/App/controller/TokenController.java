package com.auth.App.controller;

import com.auth.App.dto.AuthRequestDto;
import com.auth.App.dto.RefreshTokenDto;
import com.auth.App.entities.RefreshToken;
import com.auth.App.services.JWTService;
import com.auth.App.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class TokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenDto req) {
        return refreshTokenService.findByToken(req.getToken())
          .map(refreshToken -> {
              System.out.println("Found refresh token for user: " + refreshToken);
              return refreshTokenService.verifyExpiration(refreshToken);
          })
          .map(RefreshToken::getUser)
          .map(userInfo -> {
              String jwtToken = jwtService.generateToken(userInfo.getUsername());
              System.out.println("Generated new JWT token for user: " + userInfo.getUsername());
              return ResponseEntity.ok(Map.of("token", jwtToken, "refreshToken", req.getToken()));
          })
        .orElseThrow(() -> new RuntimeException("Refresh token not found"));
    }
}
