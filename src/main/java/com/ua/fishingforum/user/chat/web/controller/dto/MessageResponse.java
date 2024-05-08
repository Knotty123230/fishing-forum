package com.ua.fishingforum.user.chat.web.controller.dto;

import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;

import java.time.Instant;

public record MessageResponse(
        Long id,
        String content,
        UserProfileResponse fromMessage,
        Instant createdAt
) {
}
