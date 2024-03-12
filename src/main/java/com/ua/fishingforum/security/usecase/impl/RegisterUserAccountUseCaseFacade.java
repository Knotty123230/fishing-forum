package com.ua.fishingforum.security.usecase.impl;

import com.ua.fishingforum.security.mapper.RegisterRequestToUserAccountMapper;
import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.service.UserAccountService;
import com.ua.fishingforum.security.usecase.RegisterUserAccountUseCase;
import com.ua.fishingforum.security.web.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserAccountUseCaseFacade implements RegisterUserAccountUseCase {
    private final UserAccountService userAccountService;
    private final RegisterRequestToUserAccountMapper registerRequestToUserAccountMapper;

    @Override
    public void register(RegisterRequest registerRequest) {
        UserAccount userAccount = registerRequestToUserAccountMapper.map(registerRequest);
        this.userAccountService.createUserAccount(userAccount);
    }
}
