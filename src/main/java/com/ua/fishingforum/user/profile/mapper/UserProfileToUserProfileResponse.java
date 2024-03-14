package com.ua.fishingforum.user.profile.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;

public interface UserProfileToUserProfileResponse extends Mapper<UserProfileResponse, UserProfile> {
}
