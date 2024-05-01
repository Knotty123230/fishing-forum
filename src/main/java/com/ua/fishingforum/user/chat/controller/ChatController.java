package com.ua.fishingforum.user.chat.controller;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.chat.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.controller.dto.MessageResponse;
import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.chat.service.ChatService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ChatController {
    public static final String CHAT_FETCH_SUBSCRIBE = "/chat/{chatId}/join";
    public static final String CHAT_FETCH_MESSAGE = "/chat/{chatId}/message";
    private static final String EXCEPTION_MESSAGE = "чат з айді %s не знайдено";
    public static final String FETCH_ALL_CHAT_MESSAGES = "/chat/{chatId}/messages";
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    @SubscribeMapping(CHAT_FETCH_SUBSCRIBE)
    public void subscribeChat(@DestinationVariable("chatId") Long chatId) {
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        UserProfile currentUserProfile = currentUserProfileApiService.currentUserProfile();
        Chat createdChat = chat;
        if (!chat.getMembers().contains(currentUserProfile)) {
            chat.getMembers().add(currentUserProfile);
            createdChat = chatService.createChat(chat);
        }

        simpMessagingTemplate.convertAndSend(
                "/topic/chat/" + createdChat.getId() + "/join",
                createdChat
        );
    }

    @MessageMapping(CHAT_FETCH_MESSAGE)
    public void handleChatMessage(@DestinationVariable("chatId") Long chatId, MessageRequest messageRequest) {
        UserProfile currentUserProfile = currentUserProfileApiService.currentUserProfile();
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        Message message = Message
                .builder()
                .from(currentUserProfile)
                .content(messageRequest.content())
                .build();
        chat.getMessages()
                .add(message);
        Chat savedChat = chatService.createChat(chat);

        simpMessagingTemplate.convertAndSend(
                "/chat/" + savedChat.getId() + "/messages",
                new MessageResponse(
                        message.getId(),
                        message.getContent(),
                        new UserProfileResponse(message.getFrom().getNickname(),
                                message.getFrom().getImageLink()
                        )
                ));
    }
    @SubscribeMapping(FETCH_ALL_CHAT_MESSAGES)
    public void fetchSubscribeOnChatMessages(@DestinationVariable("chatId") Long chatId) {
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));

        List<MessageResponse> messageResponses = chat.getMessages().stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .limit(20)
                .map(message -> new MessageResponse(
                        message.getId(),
                        message.getContent(),
                        new UserProfileResponse(
                                message.getFrom().getNickname(),
                                message.getFrom().getImageLink()
                        )
                ))
                .toList();


        simpMessagingTemplate.convertAndSend(
                "/chat/" + chatId + "/messages",
                messageResponses
        );
    }

}
