package com.ua.fishingforum.user.chat.web.controller.dto;

import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;

public record MessageResponse(
        Long id,
        String content,
        UserProfileResponse fromMessage,
        long createdAt
) {
}
