package com.ua.fishingforum.user.chat.web.controller;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.chat.service.ChatService;
import com.ua.fishingforum.user.chat.service.MessageService;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    public static final String CHAT_FETCH_MESSAGE = "/chat/{chatId}/message";
    public static final String FETCH_ALL_CHAT_MESSAGES = "/chat/{chatId}/messages";
    private static final String EXCEPTION_MESSAGE = "чат з айді %s не знайдено";
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final MessageService messageService;


    @MessageMapping(CHAT_FETCH_MESSAGE)
    public void handleChatMessage(@DestinationVariable("chatId") Long chatId, MessageRequest messageRequest, @Headers Map<String, Object> headers) {
        JwtAuthenticationToken simpUser = (JwtAuthenticationToken) headers.get("simpUser");
        SecurityContextHolder.getContext().setAuthentication(simpUser);

        UserProfile currentUserProfile = currentUserProfileApiService.currentUserProfile();
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        Message message = Message
                .builder()
                .from(currentUserProfile)
                .chat(chat)
                .content(messageRequest.content())
                .build();
        Message savedMessage = messageService.save(message);
        simpMessagingTemplate.convertAndSend(
                "/chat/" + chat.getId() + "/messages",
                new MessageResponse(
                        savedMessage.getId(),
                        savedMessage.getContent(),
                        new UserProfileResponse(savedMessage.getFrom().getNickname(),
                                savedMessage.getFrom().getImageLink()
                        ),
                        savedMessage.getCreatedAt()
                ));
        log.info(savedMessage.toString());
    }

    @SubscribeMapping(FETCH_ALL_CHAT_MESSAGES)
    public List<MessageResponse> fetchSubscribeOnChatMessages(@DestinationVariable("chatId") Long chatId) {
        // Отримати чат за його ідентифікатором
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));

        // Отримати останні повідомлення у чаті та перетворити їх у відповіді
        return chat.getMessages().stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .limit(20)
                .map(this::convertToMessageResponse)
                .toList();
    }

    private MessageResponse convertToMessageResponse(Message message) {
        UserProfileResponse userProfileResponse = new UserProfileResponse(
                message.getFrom().getNickname(),
                message.getFrom().getImageLink()
        );
        return new MessageResponse(
                message.getId(),
                message.getContent(),
                userProfileResponse,
                message.getCreatedAt()
        );
    }

}
