package com.ua.fishingforum.user.chat.repository;

import com.ua.fishingforum.user.chat.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
