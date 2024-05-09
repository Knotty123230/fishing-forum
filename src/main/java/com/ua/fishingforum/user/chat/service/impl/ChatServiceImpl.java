package com.ua.fishingforum.user.chat.service.impl;

import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.repository.ChatRepository;
import com.ua.fishingforum.user.chat.service.ChatService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public void addMemberToChat(Chat chat, UserProfile currentUserProfile) {
        chat.getMembers()
                .add(currentUserProfile);
    }

    @Override
    public Chat updateChat(Chat chat) {
        return chatRepository.saveAndFlush(chat);
    }

    @Override
    public Optional<Chat> findChatByName(String chatName) {
        return chatRepository.findByName(chatName);
    }

    @Override
    public Optional<List<Chat>> findAllChatsForCurrentUser(UserProfile userProfile) {
        return this.chatRepository.findChatsByMembers(userProfile);
    }
}
