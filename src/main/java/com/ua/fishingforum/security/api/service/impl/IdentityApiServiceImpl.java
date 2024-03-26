package com.ua.fishingforum.security.api.service.impl;


import com.ua.fishingforum.security.api.model.CurrentUserApiModel;
import com.ua.fishingforum.security.api.service.IdentityApiService;
import com.ua.fishingforum.security.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdentityApiServiceImpl implements IdentityApiService {
    private final UserAccountService accountService;

    @Override
    public Optional<CurrentUserApiModel> currentUserAccount() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Principal::getName)
                .flatMap(this.accountService::findUserByUsername)
                .map(it -> new CurrentUserApiModel(it.getId()));
    }
}
