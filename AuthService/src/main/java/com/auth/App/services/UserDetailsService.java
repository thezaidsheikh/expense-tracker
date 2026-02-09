package com.auth.App.services;

import com.auth.App.entities.UserInfo;
import com.auth.App.eventProducer.UserInfoProducer;
import com.auth.App.model.UserInfoDto;
import com.auth.App.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Data
@AllArgsConstructor
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoProducer userInfoProducer;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetailsService(user);
    }

    public UserInfo checkIfUserExists(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean signupUser(UserInfoDto user) {
        if(checkIfUserExists(user.getUsername()) != null) {
            return false; // User already exists
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(new UserInfo(user.getUserId(),user.getUsername(), user.getPassword(), new HashSet<>()));
        userInfoProducer.sendEventToKafka(user);
        return true;
    }
}
