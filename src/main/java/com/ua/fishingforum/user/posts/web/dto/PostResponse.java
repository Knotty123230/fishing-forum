package com.ua.fishingforum.user.posts.web.dto;

import com.ua.fishingforum.user.profile.model.UserProfile;

import java.time.Instant;
import java.util.List;

public record PostResponse(
        String name,
        String description,
        List<PhotoResponse> images,
        UserProfile userProfile,
        Instant createdAt
) {

}
