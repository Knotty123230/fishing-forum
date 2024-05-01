package com.ua.fishingforum.user.chat.service;

import com.ua.fishingforum.user.chat.model.Chat;

import java.util.Optional;

public interface ChatService {
    Chat createChat(Chat chat);

    Optional<Chat> findChatById(Long chatId);
}
