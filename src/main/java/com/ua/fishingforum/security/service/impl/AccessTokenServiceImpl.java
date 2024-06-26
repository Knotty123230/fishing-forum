package com.ua.fishingforum.security.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.service.AccessTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {
    private final JwtEncoder jwtEncoder;

    @Override
    public String generateIdToken(Authentication authentication) {
        UserDetails userDetails = Optional.of(authentication.getPrincipal())
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElseThrow(() ->
                        new CustomException("не вдалось сформувати об'єкт UserDetails з об'єкта Authentication"));

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        UserAccount principal = (UserAccount) authentication.getPrincipal();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .claim("scope", roles)
                .claim("username", principal.getUsername())
                .claim("name", principal.getFirstName())
                .claim("lastName", principal.getLastName())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .subject(userDetails.getUsername())
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
