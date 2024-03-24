package com.ua.fishingforum.security.web;

import com.ua.fishingforum.security.usecase.RegisterUserAccountUseCase;
import com.ua.fishingforum.security.web.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.ua.fishingforum.common.constants.MappingConstants.REGISTRATION_MAPPING;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class UserAccountController {
    private final RegisterUserAccountUseCase registerUserAccountUseCase;


    @PostMapping(REGISTRATION_MAPPING)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create account for user", tags = "create account")
    public void registerAccount(@Valid @RequestBody RegisterRequest registerRequest) {
        registerUserAccountUseCase.register(registerRequest);
    }

}
