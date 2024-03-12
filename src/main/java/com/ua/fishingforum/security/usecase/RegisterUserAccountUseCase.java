package com.ua.fishingforum.security.usecase;


import com.ua.fishingforum.security.web.dto.RegisterRequest;

public interface RegisterUserAccountUseCase {
    void register(RegisterRequest registerRequest);
}
