package com.ua.fishingforum.security.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.service.AccessTokenService;
import com.ua.fishingforum.security.usecase.AuthenticationUseCase;
import com.ua.fishingforum.security.web.dto.AccessToken;
import com.ua.fishingforum.security.web.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationUseCaseFacade implements AuthenticationUseCase {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;

    @Override
    public AccessToken authenticate(LoginRequest loginRequest) {
        Authentication authentication;
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            authentication = this.authenticationManager.authenticate(authToken);
        } catch (BadCredentialsException e) {
            throw new CustomException("логін або пароль введено неправильно");
        }
        String generatedIdToken = this.accessTokenService.generateIdToken(authentication);
        return new AccessToken(generatedIdToken);
    }
}
