package com.ua.fishingforum.user.chat.usecase;

import com.ua.fishingforum.user.chat.controller.dto.ChatRequest;
import com.ua.fishingforum.user.chat.controller.dto.ChatResponse;

public interface ChatUseCase {


    ChatResponse createChat(ChatRequest chatRequest);
}
