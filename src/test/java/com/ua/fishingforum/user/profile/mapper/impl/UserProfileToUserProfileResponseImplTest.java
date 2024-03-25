package com.ua.fishingforum.user.profile.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileToUserProfileResponseImplTest {
    @Mock
    UserProfileService userProfileService;
    @InjectMocks
    UserProfileToUserProfileResponseImpl userProfileToUserProfileResponse;

    private static UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("nickname");
        userProfile.setImageLink(new Photo("imageLink"));
        return userProfile;
    }

    @Test
    void map_shouldReturnUserProfileResponse() {
        UserProfile userProfile = getUserProfile();
        when(userProfileService.existsByNickname(any())).thenReturn(false);
        when(userProfileService.editUserProfile(userProfile)).thenReturn(userProfile);

        UserProfileResponse map = userProfileToUserProfileResponse.map(userProfile);
        assertEquals(map.nickname(), userProfile.getNickname());
        assertEquals(userProfile.getImageLink(), map.imageUrl());
        verify(userProfileService, times(1)).editUserProfile(any());
        verify(userProfileService, times(1)).editUserProfile(any());
    }

    @Test
    void map_shouldThrowExceptionWhenNicknameNotExists() {
        when(userProfileService.existsByNickname(any())).thenReturn(true);
        CustomException customException = assertThrows(CustomException.class, this::mapUserProfile);

        assertEquals("юзер з таким нікнеймом: nickname уже існує", customException.getMessage());
        verify(userProfileService, times(1)).existsByNickname(any());
        verify(userProfileService, times(0)).editUserProfile(any());
    }

    private void mapUserProfile() {
        userProfileToUserProfileResponse.map(getUserProfile());
    }

}