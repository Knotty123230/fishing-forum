package com.ua.fishingforum.user.chat.hadler;

import java.util.Map;

public interface JoinChatAuthenticationHandler {

    void authenticate(Map<String, Object> headers);
}
