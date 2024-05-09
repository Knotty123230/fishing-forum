package com.ua.fishingforum.user.chat.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;

public interface MessageToMessageResponseMapper extends Mapper<MessageResponse, Message> {
}
