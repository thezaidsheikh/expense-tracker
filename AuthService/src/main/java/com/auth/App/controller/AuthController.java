package com.auth.App.controller;

import com.auth.App.dto.AuthRequestDto;
import com.auth.App.entities.RefreshToken;
import com.auth.App.model.UserInfoDto;
import com.auth.App.services.JWTService;
import com.auth.App.services.RefreshTokenService;
import com.auth.App.services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController("/auth")
public class AuthController {

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final RefreshTokenService refreshTokenService;

    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserInfoDto req) {
        try{
            boolean isSignedUp = userDetailsService.signupUser(req);
            if(!isSignedUp) {
                return ResponseEntity.badRequest().body("User already exists");
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(req.getUsername());
            String jwtToken = jwtService.generateToken(req.getUsername());
            return ResponseEntity.ok(Map.of("jwtToken", jwtToken, "refresh;Token", refreshToken.getToken()));
        } catch(Exception ex) {
            return ResponseEntity.internalServerError().body("An error occurred during signup");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto req) {
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            if(authenticate.isAuthenticated()) {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(req.getUsername());
                String jwtToken = jwtService.generateToken(req.getUsername());
                return ResponseEntity.ok(Map.of("jwtToken", jwtToken, "refresh;Token", refreshToken.getToken()));
            }
            return ResponseEntity.badRequest().body("Invalid credentials");
        } catch(Exception ex) {
            return ResponseEntity.internalServerError().body("An error occurred during signup");
        }
    }
}
