package com.ua.fishingforum.user.profile.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.mapper.UserProfileToUserProfileResponse;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.usecase.EditUserProfileUseCase;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class EditUserProfileUseCaseFacade implements EditUserProfileUseCase {
    private final UserProfileService userProfileService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final UserProfileToUserProfileResponse userProfileToUserProfileResponse;
    @Override
    public UserProfileResponse editUserProfile(String nickname, UserProfileRequest userProfileRequest) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        UserProfile findedUserProfile = this.userProfileService.findUserProfileByNickname(nickname)
                .orElseThrow(() -> new CustomException("юзера з таким нікнеймом %s не знайдено".formatted(nickname)));
        if (!Objects.equals(userProfile.getNickname(), findedUserProfile.getNickname())){
            throw new CustomException("корегування чужого профілю заборонено");
        }
        findedUserProfile.setNickname(userProfileRequest.nickname());
        findedUserProfile.setImageLink(userProfileRequest.imageLink());
        return userProfileToUserProfileResponse.map(findedUserProfile);
    }
}
