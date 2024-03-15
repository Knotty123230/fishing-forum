package com.ua.fishingforum.user.profile.api.service.impl;


import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.api.model.CurrentUserApiModel;
import com.ua.fishingforum.security.api.service.IdentityApiService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserProfileApiServiceImpl implements CurrentUserProfileApiService {
    private final IdentityApiService identityApiService;
    private final UserProfileService userProfileService;

    @Override
    public UserProfile currentUserProfile() {
        CurrentUserApiModel currentUserApiModel = identityApiService.currentUserAccount().orElseThrow(() ->
                new CustomException("користувач повинен бути авторизований"));
        return this.userProfileService.findUserProfileById(currentUserApiModel.userAccountId());
    }
}
