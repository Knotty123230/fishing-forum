package com.ua.fishingforum.user.chat.mapper.impl;

import com.ua.fishingforum.user.chat.mapper.ChatToChatResponseMapper;
import com.ua.fishingforum.user.chat.mapper.MessageToMessageResponseMapper;
import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatResponse;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatToChatResponseMapperImpl implements ChatToChatResponseMapper {
    private final MessageToMessageResponseMapper messageToMessageResponseMapper;

    @Override
    public ChatResponse map(Chat source) {
        return new ChatResponse(source.getId(),
                source.getName(),
                source.getMembers().stream().map(member -> new UserProfileResponse(member.getNickname(), member.getImageLink())).toList(),
                source.getMessages().stream().map(this.messageToMessageResponseMapper::map).toList());
    }
}
