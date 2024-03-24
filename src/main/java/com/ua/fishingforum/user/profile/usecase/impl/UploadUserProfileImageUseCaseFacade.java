package com.ua.fishingforum.user.profile.usecase.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.mapper.UserProfileToImageUserProfileResponseMapper;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.usecase.UploadUserProfileImageUseCase;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadUserProfileImageUseCaseFacade implements UploadUserProfileImageUseCase {
    private final UserProfileService userProfileService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final ImageStorageService imageStorageService;
    private final UserProfileToImageUserProfileResponseMapper userProfileToImageUserProfileResponseMapper;


    @Override
    public UserProfileResponse upload(MultipartFile multipartFile) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        String imageUrl = imageStorageService.uploadImage(multipartFile);
        userProfile.setImageLink(imageUrl);
        userProfileService.editUserProfile(userProfile);
        return userProfileToImageUserProfileResponseMapper.map(userProfile);
    }
}
