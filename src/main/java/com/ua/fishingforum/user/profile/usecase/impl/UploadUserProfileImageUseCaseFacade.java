package com.ua.fishingforum.user.profile.usecase.impl;

import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.posts.image.service.PhotoService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.mapper.UserProfileToImageUserProfileResponseMapper;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.usecase.UploadUserProfileImageUseCase;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadUserProfileImageUseCaseFacade implements UploadUserProfileImageUseCase {
    private final UserProfileService userProfileService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final UserProfileToImageUserProfileResponseMapper userProfileToImageUserProfileResponseMapper;
    private final PhotoService photoService;


    @Override
    @Transactional
    public UserProfileResponse upload(MultipartFile multipartFile) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Photo photo = photoService.uploadPhoto(multipartFile);
        userProfile.setImageLink(photo);
        userProfileService.editUserProfile(userProfile);
        return userProfileToImageUserProfileResponseMapper.map(userProfile);
    }
}
