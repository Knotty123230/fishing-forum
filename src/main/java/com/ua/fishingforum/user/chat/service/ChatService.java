package com.ua.fishingforum.user.chat.service;

import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.profile.model.UserProfile;

import java.util.Optional;

public interface ChatService {
    Chat createChat(Chat chat);

    Optional<Chat> findChatById(Long chatId);

    void addMemberToChat(Chat chat, UserProfile currentUserProfile);

    Chat updateChat(Chat chat);

    Optional<Chat> findChatByName(String chatName);
}
