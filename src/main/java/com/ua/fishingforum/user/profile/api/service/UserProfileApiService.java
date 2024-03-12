package com.ua.fishingforum.user.profile.api.service;


import com.ua.fishingforum.user.profile.model.UserProfile;

public interface UserProfileApiService {
    UserProfile findUserProfileById(Long id);
}
