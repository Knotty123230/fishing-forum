package com.ua.fishingforum.user.chat.service.impl;

import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.repository.ChatRepository;
import com.ua.fishingforum.user.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Optional<Chat> findChatById(Long chatId) {
        return chatRepository.findById(chatId);
    }
}
