package com.ua.fishingforum.user.profile.mapper.impl;

import com.ua.fishingforum.user.profile.mapper.UserProfileToImageUserProfileResponseMapper;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserProfileToImageUserProfileResponseMapperImpl implements UserProfileToImageUserProfileResponseMapper {
    @Override
    public UserProfileResponse map(UserProfile source) {
        return new UserProfileResponse(source.getNickname(), source.getImageLink());
    }
}
