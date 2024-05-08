package com.ua.fishingforum.user.profile.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.repository.UserProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    private UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("nickname");
        userProfile.setImageLink(new Photo("imageLink"));
        return userProfile;
    }

    @Test
    void createUserProfile_shouldCreateUserProfile() {
        UserProfile userProfile = getUserProfile();
        when(userProfileRepository.existsById(any())).thenReturn(false);
        when(userProfileRepository.existsByNickname(any())).thenReturn(false);
        when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

        userProfileService.createUserProfile(userProfile);

        verify(userProfileRepository).existsById(any());
        verify(userProfileRepository).existsByNickname(any());
        verify(userProfileRepository).save(userProfile);
    }

    @Test
    void createUserProfile_shouldThrowExceptionWhenUserWithIdAlreadyExists() {
        when(userProfileRepository.existsById(any())).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, this::getProfile);
        assertEquals("Профіль користувача з таким id 1 уже був створений", customException.getMessage());

        verify(userProfileRepository).existsById(any());
    }

    @Test
    void createUserProfile_shouldThrowExceptionWhenUserWithNicknameAlreadyExists() {
        when(userProfileRepository.existsById(any())).thenReturn(false);
        when(userProfileRepository.existsByNickname(any())).thenReturn(true);

        CustomException customException = assertThrows(CustomException.class, this::getProfile);
        assertEquals("профіль з таким нікнеймом nickname уже існує", customException.getMessage());

        verify(userProfileRepository).existsById(any());
        verify(userProfileRepository).existsByNickname(any());
    }

    private void getProfile() {
        userProfileService.createUserProfile(getUserProfile());
    }

    @Test
    void findUserProfileById_shouldSuccess() {
        when(userProfileRepository.findById(1L)).thenReturn(Optional.of(getUserProfile()));

        UserProfile userProfileById = userProfileService.findUserProfileById(1L);

        assertEquals(getUserProfile(), userProfileById);
        verify(userProfileRepository).findById(1L);
    }

    @Test
    void findUserProfileById_shouldThrowExceptionWhenRepositoryReturnEmptyUserProfile() {
        when(userProfileRepository.findById(1L)).thenReturn(Optional.empty());

        CustomException customException = assertThrows(CustomException.class, () ->
                userProfileService.findUserProfileById(1L));
        assertEquals("профіля користувача з айді 1 не існує", customException.getMessage());

        verify(userProfileRepository).findById(1L);
    }

    @Test
    void findUserProfileByNickname_ExistingNickname_ReturnsOptionalUserProfile() {
        when(userProfileRepository.findByNickname("nickname")).thenReturn(Optional.of(getUserProfile()));

        Optional<UserProfile> result = userProfileService.findUserProfileByNickname("nickname");

        assertTrue(result.isPresent());
        assertEquals(getUserProfile(), result.get());
    }

    @Test
    void findUserProfileByNickname_NonExistingNickname_ReturnsEmptyOptional() {
        when(userProfileRepository.findByNickname("nickname")).thenReturn(Optional.empty());

        Optional<UserProfile> result = userProfileService.findUserProfileByNickname("nickname");

        assertTrue(result.isEmpty());
    }

    @Test
    void editUserProfile_ValidUserProfile_ReturnsEditedUserProfile() {
        UserProfile userProfile = getUserProfile();
        when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

        UserProfile result = userProfileService.editUserProfile(userProfile);

        assertEquals(userProfile, result);
        verify(userProfileRepository).save(userProfile);
    }

    @Test
    void existsByNickname_NicknameExists_ReturnsTrue() {
        String existingNickname = "testNickname";
        when(userProfileRepository.existsByNickname(existingNickname)).thenReturn(true);

        boolean result = userProfileService.existsByNickname(existingNickname);

        assertTrue(result);
    }

    @Test
    void existsByNickname_NicknameDoesNotExist_ReturnsFalse() {
        String nonExistingNickname = "nonExistingNickname";
        when(userProfileRepository.existsByNickname(nonExistingNickname)).thenReturn(false);

        boolean result = userProfileService.existsByNickname(nonExistingNickname);

        assertFalse(result);
    }
}
