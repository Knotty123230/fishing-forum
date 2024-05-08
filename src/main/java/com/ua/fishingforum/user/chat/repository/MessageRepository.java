package com.ua.fishingforum.user.chat.repository;

import com.ua.fishingforum.user.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
