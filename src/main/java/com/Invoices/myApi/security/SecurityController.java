package com.Invoices.myApi.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Invoices.myApi.models.LoginRequest;
import com.Invoices.myApi.models.LoginResponse;
import com.Invoices.myApi.models.RegisterRequest;
import com.Invoices.myApi.models.RegisterResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Tag(name = "Security")

public class SecurityController {
        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        CustomUserDetailService userDetailService;

        @Autowired
        JwtEncoder jwtEncoder;

        @Value("${jwt.expiration}")
        private long jwtExpiration;

        @Operation(summary = "Login to the application")
        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
                Authentication authentication = authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(),
                                                loginRequest.password()));

                long userId = ((CustomUserDetail) authentication.getPrincipal()).getId();
                Instant instant = Instant.now();

                String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(" "));

                JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                                .issuedAt(instant)
                                .expiresAt(instant.plus(jwtExpiration, ChronoUnit.MILLIS))
                                .subject(loginRequest.username())
                                .claim("scope", scope)
                                .claim("userId", userId)
                                .build();

                JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                                JwsHeader.with(MacAlgorithm.HS256).build(),
                                jwtClaimsSet);
                String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

                LoginResponse loginResponse = new LoginResponse(userId, jwt, authentication.getName());
                return ResponseEntity.ok(loginResponse);
        }

        @Operation(summary = "Register a new user")
        @PostMapping("/register")
        public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
                RegisterResponse registerResponse = userDetailService.registerUser(registerRequest);
                return ResponseEntity.ok(registerResponse);
        }

}
