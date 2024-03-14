package com.ua.fishingforum.user.profile.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.profile.mapper.UserProfileToUserProfileResponse;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileToUserProfileResponseImpl implements UserProfileToUserProfileResponse {
    private final UserProfileService userProfileService;
    @Override
    public UserProfileResponse map(UserProfile source) {
        boolean existsByNickname = userProfileService.existsByNickname(source.getNickname());
        if (existsByNickname){
            throw new CustomException("юзер з таким нікнеймом: %s уже існує".formatted(source.getNickname()));
        }
        UserProfile editedUserProfile = userProfileService.editUserProfile(source);
        return new UserProfileResponse(editedUserProfile.getNickname(),
                editedUserProfile.getImageLink());
    }
}
