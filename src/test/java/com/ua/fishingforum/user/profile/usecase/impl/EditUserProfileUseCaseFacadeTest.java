package com.ua.fishingforum.user.profile.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.mapper.UserProfileToUserProfileResponse;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditUserProfileUseCaseFacadeTest {
    @Mock
    UserProfileService userProfileService;
    @Mock
    CurrentUserProfileApiService currentUserProfileApiService;
    @Mock
    UserProfileToUserProfileResponse userProfileToUserProfileResponse;
    @InjectMocks
    EditUserProfileUseCaseFacade editUserProfileUseCaseFacade;

    private static UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("nickname");
        userProfile.setImageLink("imageLink");
        return userProfile;
    }

    @Test
    void editUserProfile_shouldSuccessReturnUserProfileResponse() {
        UserProfile userProfile = getUserProfile();
        UserProfileResponse userProfileResponse1 = new UserProfileResponse("nickname1", "imageLink");
        UserProfileRequest userProfileRequest = new UserProfileRequest("nickname1");

        when(currentUserProfileApiService.currentUserProfile()).thenReturn(userProfile);
        when(userProfileService.findUserProfileByNickname(userProfile.getNickname())).thenReturn(Optional.of(userProfile));
        when(userProfileToUserProfileResponse.map(userProfile)).thenReturn(userProfileResponse1);

        UserProfileResponse userProfileResponse = this.editUserProfileUseCaseFacade.editUserProfile("nickname", userProfileRequest);
        assertEquals(userProfileRequest.nickname(), userProfileResponse.nickname());

        verify(this.userProfileService, times(1)).findUserProfileByNickname(anyString());
        verify(currentUserProfileApiService, times(1)).currentUserProfile();
        verify(userProfileToUserProfileResponse, times(1)).map(any());
    }

    @Test
    void editUserProfile_shouldThrowExceptionWhenFindUserProfileByNickname() {
        when(this.currentUserProfileApiService.currentUserProfile()).thenReturn(getUserProfile());
        when(userProfileService.findUserProfileByNickname(anyString())).thenReturn(Optional.empty());
        CustomException customException = assertThrows(CustomException.class, this::editUserProfile);
        assertEquals("юзера з таким нікнеймом nickname1 не знайдено", customException.getMessage());

        verify(currentUserProfileApiService, times(1)).currentUserProfile();
        verify(userProfileService, times(1)).findUserProfileByNickname(anyString());
        verify(userProfileToUserProfileResponse, times(0)).map(any());
    }
    @Test
    void editUserProfile_shouldThrowExceptionWhenUserProfilesNotEqual() {
        UserProfile userProfile = new UserProfile();
        userProfile.setNickname("nickname1");
        userProfile.setId(2L);
        userProfile.setImageLink("imageLink");

        when(this.currentUserProfileApiService.currentUserProfile()).thenReturn(getUserProfile());
        when(this.userProfileService.findUserProfileByNickname(any())).thenReturn(Optional.of(userProfile));

        CustomException customException = assertThrows(CustomException.class, this::editUserProfile);
        assertEquals("корегування чужого профілю заборонено", customException.getMessage());
        verify(currentUserProfileApiService, times(1)).currentUserProfile();
        verify(userProfileService, times(1)).findUserProfileByNickname(any());
        verify(userProfileToUserProfileResponse, times(0)).map(any());
    }

    private void editUserProfile() {
        this.editUserProfileUseCaseFacade.editUserProfile("nickname1", new UserProfileRequest("nickname1"));
    }
}
