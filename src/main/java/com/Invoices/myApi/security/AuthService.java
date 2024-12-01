package com.Invoices.myApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Invoices.myApi.errors.UnauthorizedException;

@Service
public class AuthService {
    @Autowired
    private JwtDecoder jwtDecoder;

    public long getUserId() {
        String jwtToken = getJwtTokenFromHeader();
        return Long.parseLong(jwtDecoder.decode(jwtToken).getClaimAsString("userId"));
    };

    private String getJwtTokenFromHeader() {
        String authHeader = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        throw new UnauthorizedException("Authorization header is missing or invalid");
    };
}
