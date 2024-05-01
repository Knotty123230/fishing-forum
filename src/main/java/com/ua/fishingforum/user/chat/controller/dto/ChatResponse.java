package com.ua.fishingforum.user.chat.controller.dto;

import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;

import java.util.List;

public record ChatResponse(
        Long id ,
        String name,
        List<UserProfileResponse> userProfileResponses,
        List<MessageResponse> messages
) {
}
