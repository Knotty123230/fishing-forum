package com.ua.fishingforum.user.chat.mapper.impl;

import com.ua.fishingforum.user.chat.mapper.MessageToMessageResponseMapper;
import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class MessageToMessageResponseMapperImpl implements MessageToMessageResponseMapper {
    @Override
    public MessageResponse map(Message message) {
        return new MessageResponse(
                message.getId(),
                message.getContent(),
                new UserProfileResponse(
                        message.getFrom() != null ? message.getFrom().getNickname() : "",
                        message.getFrom() != null ? message.getFrom().getImageLink() : new Photo("")
                ),
                message.getCreatedAt().toEpochMilli()
        );
    }
}
