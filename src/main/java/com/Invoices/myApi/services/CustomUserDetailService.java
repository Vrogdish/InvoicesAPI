package com.Invoices.myApi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Invoices.myApi.entities.UserEntity;
import com.Invoices.myApi.enums.Role;
import com.Invoices.myApi.models.CustomUserDetail;
import com.Invoices.myApi.models.RegisterRequest;
import com.Invoices.myApi.models.RegisterResponse;
import com.Invoices.myApi.repositories.CustomUserDetailRepository;

@Service

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    CustomUserDetailRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetail(userEntity);
    }

    public RegisterResponse registerUser(RegisterRequest registerRequest) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(registerRequest.username());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with username: " + registerRequest.username());
        }

        UserEntity newUser = UserEntity.builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password())).build()

        ;
        newUser.getRoles().add(Role.ROLE_USER);

        UserEntity registeredUser = userRepository.save(newUser);

        return new RegisterResponse(
                registeredUser.getId(),
                registeredUser.getUsername(),
                registeredUser.getEmail());

    }

}
