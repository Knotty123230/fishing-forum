package com.ua.fishingforum.user.chat.usecase.impl;

import com.ua.fishingforum.user.chat.controller.dto.ChatRequest;
import com.ua.fishingforum.user.chat.controller.dto.ChatResponse;
import com.ua.fishingforum.user.chat.controller.dto.MessageResponse;
import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.service.ChatService;
import com.ua.fishingforum.user.chat.usecase.ChatUseCase;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatUseCaseFacade implements ChatUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final ChatService chatService;

    @Override
    public ChatResponse createChat(ChatRequest chatRequest) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();

        Chat chat = Chat.builder()
                .members(List.of(userProfile))
                .name(chatRequest.name())
                .build();

        Chat createdChat = chatService.createChat(chat);
        return new ChatResponse(
                createdChat.getId(),
                createdChat.getName(),
                createdChat.getMembers()
                        .stream()
                        .map(profile -> new UserProfileResponse(profile.getNickname(), profile.getImageLink()))
                        .toList(),
                createdChat.getMessages()
                        .stream()
                        .map(message -> new MessageResponse(
                                message.getId(),
                                message.getContent(),
                                new UserProfileResponse(
                                        message.getFrom().getNickname(),
                                        message.getFrom().getImageLink()
                                )
                        ))
                        .toList()
        );
    }
}
