package com.ua.fishingforum.user.profile.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.api.model.CurrentUserApiModel;
import com.ua.fishingforum.security.api.service.IdentityApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileRequest;
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
class UserProfileRequestToUserProfileMapperImplTest {
    @Mock
    IdentityApiService identityApiService;
    @InjectMocks
    UserProfileRequestToUserProfileMapperImpl userProfileRequestToUserProfileMapper;

    private static UserProfileRequest getUserProfileRequest() {
        return new UserProfileRequest("nickname");
    }

    @Test
    void map_shouldReturnUserProfile() {
        CurrentUserApiModel currentUserApiModel = new CurrentUserApiModel(1L);

        when(identityApiService.currentUserAccount()).thenReturn(Optional.of(currentUserApiModel));

        UserProfileRequest nickname = getUserProfileRequest();
        UserProfile userProfile = userProfileRequestToUserProfileMapper.map(nickname);

        assertEquals(nickname.nickname(), userProfile.getNickname());
        verify(identityApiService, times(1)).currentUserAccount();
    }

    @Test
    void map_shouldThrowException() {
        when(identityApiService.currentUserAccount()).thenReturn(Optional.empty());

        UserProfileRequest userProfileRequest = getUserProfileRequest();

        assertThrows(CustomException.class,
                () -> mapUserProfileRequest(userProfileRequest),
                "для створення профіля користувач має бути авторизований в системі");
    }

    private void mapUserProfileRequest(UserProfileRequest userProfileRequest) {
        userProfileRequestToUserProfileMapper.map(userProfileRequest);
    }
}