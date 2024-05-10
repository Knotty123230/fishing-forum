package com.ua.fishingforum.user.chat.usecase;

import com.ua.fishingforum.user.chat.web.controller.dto.ChatRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatResponse;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;

import java.util.List;
import java.util.Map;

public interface ChatUseCase {


    void createChat(Map<String, Object> headers, ChatRequest chatRequest);

    void handleMessage(Map<String, Object> headers, Long chatId, MessageRequest messageRequest);

    List<MessageResponse> handleSubscribeChatMessage(Map<String, Object> headers, Long chatId);

    List<ChatResponse> fetchAllChatsForCurrentUser();

    void fetchJoinChat(Map<String, Object> headers, Long chatId, MessageRequest messageRequest);

    ChatResponse fetchJoinChatMemberAndSendMessage(Long chatId);
}
