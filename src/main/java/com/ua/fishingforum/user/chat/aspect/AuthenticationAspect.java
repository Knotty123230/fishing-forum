package com.ua.fishingforum.user.chat.aspect;

import com.ua.fishingforum.user.chat.hadler.JoinChatAuthenticationHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class AuthenticationAspect {
    private final JoinChatAuthenticationHandler joinChatAuthenticationHandler;

    @Before("execution(* com.ua.fishingforum.user.chat.usecase.*.*(..)) && args(headers, ..)")
    public void authenticateBeforeMethodExecution(JoinPoint joinPoint, Map<String, Object> headers) {
        log.info("user authenticated with method {}", joinPoint.getSignature());
        joinChatAuthenticationHandler.authenticate(headers);
    }
}
