package com.ua.fishingforum.security.web;

import com.ua.fishingforum.security.usecase.AuthenticationUseCase;
import com.ua.fishingforum.security.web.dto.AccessToken;
import com.ua.fishingforum.security.web.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/access_token")
    @Operation(summary = "authorization user by his credentials", tags = "authorization")
    public AccessToken getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationUseCase.authenticate(loginRequest);
    }
}
