package com.ua.fishingforum.user.profile.api.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.api.model.CurrentUserApiModel;
import com.ua.fishingforum.security.api.service.IdentityApiService;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrentUserProfileApiServiceImplTest {
    @Mock
    IdentityApiService identityApiService;

    @Mock
    UserProfileService userProfileService;
    @InjectMocks
    CurrentUserProfileApiServiceImpl currentUserProfileApiService;

    private static UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setImageLink(new Photo("imageLink"));
        userProfile.setNickname("nickname");
        return userProfile;
    }

    @Test
    void currentUserProfile_shouldReturnUserProfile() {
        CurrentUserApiModel currentUserApiModel = new CurrentUserApiModel(1L);
        UserProfile userProfile = getUserProfile();
        when(identityApiService.currentUserAccount()).thenReturn(Optional.of(currentUserApiModel));
        when(userProfileService.findUserProfileById(currentUserApiModel.userAccountId())).thenReturn(userProfile);
        UserProfile userProfile1 = currentUserProfileApiService.currentUserProfile();
        Assertions.assertEquals(userProfile, userProfile1);
        verify(identityApiService, times(1)).currentUserAccount();
        verify(userProfileService, times(1)).findUserProfileById(any());
    }

    @Test
    void currentUserProfile_shouldThrowAuthorizationException() {
        when(identityApiService.currentUserAccount()).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class, () -> currentUserProfileApiService.currentUserProfile());
        verify(identityApiService, times(1)).currentUserAccount();
    }
}