package com.ua.fishingforum.user.chat.web.controller;

import com.ua.fishingforum.user.chat.usecase.ChatUseCase;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    public static final String CHAT_FETCH_MESSAGE = "/chat/{chatId}/message";
    public static final String FETCH_ALL_CHAT_MESSAGES = "/chat/{chatId}/messages";
    public static final String FETCH_CREATE_CHAT = "/chat/create/{name}";
    private final ChatUseCase chatUseCase;


    @MessageMapping(CHAT_FETCH_MESSAGE)
    public void handleChatMessage(@DestinationVariable("chatId") Long chatId, MessageRequest messageRequest, @Headers Map<String, Object> headers) {
        this.chatUseCase.handleMessage(headers, chatId, messageRequest);
    }

    @SubscribeMapping(FETCH_ALL_CHAT_MESSAGES)
    public List<MessageResponse> fetchSubscribeOnChatMessages(@Headers Map<String, Object> headers, @DestinationVariable("chatId") Long chatId) {
        return chatUseCase.handleChatMessage(headers, chatId);
    }

    @MessageMapping(FETCH_CREATE_CHAT)
    public void fetchCreateChat(@Headers Map<String, Object> headers, ChatRequest chatRequest) {
        chatUseCase.createChat(headers, chatRequest);
    }


}
