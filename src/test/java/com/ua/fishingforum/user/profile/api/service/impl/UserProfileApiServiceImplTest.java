package com.ua.fishingforum.user.profile.api.service.impl;

import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.service.UserProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileApiServiceImplTest {
    @Mock
    UserProfileService userProfileService;

    @InjectMocks
    UserProfileApiServiceImpl userProfileApiService;


    @Test
    void findUserProfileById_shouldReturnUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setNickname("nickname");
        userProfile.setImageLink(new Photo("imageLink"));
        userProfile.setId(1L);
        when(userProfileService.findUserProfileById(1L)).thenReturn(userProfile);

        UserProfile userProfileById = userProfileApiService.findUserProfileById(1L);
        assertNotNull(userProfileById);
        assertEquals(userProfile, userProfileById);
        verify(userProfileService, times(1)).findUserProfileById(any());

    }

}