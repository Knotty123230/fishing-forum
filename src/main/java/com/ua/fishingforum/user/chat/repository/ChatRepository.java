package com.ua.fishingforum.user.chat.repository;

import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.profile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByName(String chatName);
    Optional<List<Chat>> findChatsByMembers(UserProfile userProfile);
}
