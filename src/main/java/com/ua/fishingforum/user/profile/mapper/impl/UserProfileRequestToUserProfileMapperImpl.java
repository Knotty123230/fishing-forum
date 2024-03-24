package com.ua.fishingforum.user.profile.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.api.model.CurrentUserApiModel;
import com.ua.fishingforum.security.api.service.IdentityApiService;
import com.ua.fishingforum.user.profile.mapper.UserProfileRequestToUserProfileMapper;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileRequestToUserProfileMapperImpl implements UserProfileRequestToUserProfileMapper {
    private final IdentityApiService identityApiService;

    @Override
    public UserProfile map(UserProfileRequest source) {
        CurrentUserApiModel currentUserApiModel = this.identityApiService.currentUserAccount()
                .orElseThrow(
                        () -> new CustomException("для створення профіля користувач має бути авторизований в системі")
                );

        return new UserProfile(source.nickname(), currentUserApiModel.userAccountId());
    }
}
