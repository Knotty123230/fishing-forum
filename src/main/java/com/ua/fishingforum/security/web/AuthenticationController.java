package com.ua.fishingforum.security.web;

import com.ua.fishingforum.security.usecase.AuthenticationUseCase;
import com.ua.fishingforum.security.web.dto.AccessToken;
import com.ua.fishingforum.security.web.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.ua.fishingforum.common.constants.MappingConstants.LOGIN_MAPPING;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class AuthenticationController {
    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping(LOGIN_MAPPING)
    @Operation(summary = "authorization user by his credentials", tags = "authorization")
    public AccessToken getToken(@Valid @RequestBody LoginRequest loginRequest) {
        return authenticationUseCase.authenticate(loginRequest);
    }
}
