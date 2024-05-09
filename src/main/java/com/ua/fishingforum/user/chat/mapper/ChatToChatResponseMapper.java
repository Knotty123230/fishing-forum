package com.ua.fishingforum.user.chat.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatResponse;

public interface ChatToChatResponseMapper extends Mapper<ChatResponse, Chat> {
}
