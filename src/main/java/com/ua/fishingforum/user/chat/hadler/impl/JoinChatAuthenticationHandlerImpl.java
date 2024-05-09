package com.ua.fishingforum.user.chat.hadler.impl;

import com.ua.fishingforum.user.chat.hadler.JoinChatAuthenticationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class JoinChatAuthenticationHandlerImpl implements JoinChatAuthenticationHandler {
    @Override
    public void authenticate(Map<String, Object> headers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) return;
        JwtAuthenticationToken simpUser = (JwtAuthenticationToken) headers.get("simpUser");
        log.info("authentication successful for user {}", simpUser.getName());
        SecurityContextHolder.getContext().setAuthentication(simpUser);
    }
}
