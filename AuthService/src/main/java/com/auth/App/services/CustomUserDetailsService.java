package com.auth.App.services;

import com.auth.App.entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsService extends UserInfo implements UserDetails {

    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetailsService(UserInfo user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        List<GrantedAuthority> auth = new ArrayList<>();
        user.getRoles().forEach(role -> {
            auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        });
        this.authorities = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
