package com.ua.fishingforum.user.profile.web.dto;

import com.ua.fishingforum.user.posts.image.model.Photo;

public record UserProfileResponse(
        String nickname,
        Photo imageUrl
) {
}
