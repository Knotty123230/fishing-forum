package com.ua.fishingforum.security.usecase;


import com.ua.fishingforum.security.web.dto.AccessToken;
import com.ua.fishingforum.security.web.dto.LoginRequest;

public interface AuthenticationUseCase {
    AccessToken authenticate(LoginRequest loginRequest);
}
