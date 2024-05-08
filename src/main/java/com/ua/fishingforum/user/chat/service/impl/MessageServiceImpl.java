package com.ua.fishingforum.user.chat.service.impl;

import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.chat.repository.MessageRepository;
import com.ua.fishingforum.user.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
