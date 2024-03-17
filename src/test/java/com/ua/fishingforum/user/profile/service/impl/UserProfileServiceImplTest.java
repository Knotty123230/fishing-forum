package com.ua.fishingforum.user.profile.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceImplTest {
    @Mock
    UserProfileRepository userProfileRepository;
    @InjectMocks
    UserProfileServiceImpl userProfileService;


    @Test
    void createUserProfile_shouldCreateUserProfile(){
        UserProfile userProfile = getUserProfile();
        when(userProfileRepository.existsById(1L)).thenReturn(false);
        when(userProfileRepository.existsByNickname("nickname")).thenReturn(false);

        when(userProfileRepository.save(userProfile)).thenReturn(userProfile);

        userProfileService.createUserProfile(userProfile);

        verify(userProfileRepository, times(1)).existsById(any());
        verify(userProfileRepository, times(1)).existsByNickname(any());
        verify(userProfileRepository, times(1)).save(any());

    }

    private static UserProfile getUserProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setNickname("nickname");
        userProfile.setImageLink("imageLink");
        return userProfile;
    }

    @Test
    void createUserProfile_shouldThrowExceptionWhenUserWithIdAlreadyExists(){
        when(userProfileRepository.existsById(1L)).thenReturn(true);
        CustomException customException = assertThrows(CustomException.class, this::getUserProfileServiceUserProfile);
        String message = "Профіль користувача з таким id 1 уже був створений";
        assertEquals(message, customException.getMessage());
        verify(userProfileRepository, times(1)).existsById(any());
    }
    @Test
    void createUserProfile_shouldThrowExceptionWhenUserWithNicknameAlreadyExists(){
        when(userProfileRepository.existsById(1L)).thenReturn(false);
        when(userProfileRepository.existsByNickname("nickname")).thenReturn(true);
        CustomException customException = assertThrows(CustomException.class, this::getUserProfileServiceUserProfile);
        String message = "профіль з таким нікнеймом nickname уже існує";

        assertEquals(message, customException.getMessage());
        verify(userProfileRepository, times(1)).existsById(any());
        verify(userProfileRepository, times(1)).existsByNickname(any());

    }
    @Test
    void findUserProfileById_shouldSuccess(){
        when(userProfileRepository.findById(1L)).thenReturn(Optional.of(getUserProfile()));

        UserProfile userProfileById = userProfileService.findUserProfileById(1L);
        UserProfile userProfile = getUserProfile();

        assertEquals(userProfile, userProfileById);
        verify(userProfileRepository,times(1)).findById(any());

    }
    @Test
    void findUserProfileById_shouldThrowExceptionWhenRepositoryReturnEmptyUserProfile(){
        when(userProfileRepository.findById(1L)).thenReturn(Optional.empty());

        CustomException customException = assertThrows(CustomException.class, this::findById);
        String message = "профіля користувача з айді 1 не існує";

        assertEquals(message, customException.getMessage());

        verify(userProfileRepository, times(1)).findById(any());
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
        when(userProfileRepository.save(getUserProfile())).thenReturn(getUserProfile());

        UserProfile result = userProfileService.editUserProfile(getUserProfile());

        assertEquals(getUserProfile(), result);
        verify(userProfileRepository, times(1)).save(getUserProfile());
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


    private void findById() {
        userProfileService.findUserProfileById(1L);
    }

    private void getUserProfileServiceUserProfile() {
        userProfileService.createUserProfile(getUserProfile());
    }
}