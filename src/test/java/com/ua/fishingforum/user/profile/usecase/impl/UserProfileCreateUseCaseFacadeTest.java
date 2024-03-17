package com.ua.fishingforum.user.profile.usecase.impl;

import com.ua.fishingforum.user.profile.mapper.UserProfileRequestToUserProfileMapper;
import com.ua.fishingforum.user.profile.mapper.UserProfileToUserProfileResponse;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileCreateUseCaseFacadeTest {
    @Mock
    UserProfileService userProfileService;
    @Mock
    UserProfileRequestToUserProfileMapper userProfileRequestToUserProfileMapper;
    @InjectMocks
    UserProfileCreateUseCaseFacade userProfileCreateUseCaseFacade;


    @Test
    void createUserProfile_shouldSuccessCreateUserProfile(){
        UserProfileRequest userProfileRequest = new UserProfileRequest("nickname1", "imageLink");
        UserProfile userProfile = new UserProfile();
        userProfile.setImageLink(userProfileRequest.imageLink());
        userProfile.setId(1L);
        userProfile.setNickname(userProfileRequest.nickname());

        when(userProfileRequestToUserProfileMapper.map(userProfileRequest)).thenReturn(userProfile);
        doNothing().when(userProfileService).createUserProfile(userProfile);

        this.userProfileCreateUseCaseFacade.createUserProfile(userProfileRequest);

        verify(userProfileService, times(1)).createUserProfile(any());
        verify(userProfileRequestToUserProfileMapper, times(1)).map(any());
    }
}