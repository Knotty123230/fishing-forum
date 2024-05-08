package com.ua.fishingforum.user.chat.web.controller;

import com.ua.fishingforum.user.chat.usecase.ChatUseCase;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatRestController {
    private static final String CHAT_CREATE_MAPPING = "/create";
    private final ChatUseCase chatUseCase;

    @PostMapping(CHAT_CREATE_MAPPING)
    public ChatResponse createChat(@RequestBody ChatRequest chatRequest) {
        return chatUseCase.createChat(chatRequest);
    }
}
