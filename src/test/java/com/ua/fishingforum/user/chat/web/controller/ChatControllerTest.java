package com.ua.fishingforum.user.chat.web.controller;

import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.service.ChatService;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ChatControllerTest {

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private ChatService chatService;

    @Mock
    private CurrentUserProfileApiService currentUserProfileApiService;

    @InjectMocks
    private ChatController chatController;

    @SuppressWarnings("EmptyTryBlock")
    @BeforeEach
    void setUp() {
        try (AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this)) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHandleChatMessage() {
        Long chatId = 1L;
        String messageContent = "Hello, world!";
        MessageRequest messageRequest = new MessageRequest(messageContent);

        UserProfile userProfile = new UserProfile();
        when(currentUserProfileApiService.currentUserProfile()).thenReturn(userProfile);

        Chat chat = new Chat();
        chat.setId(chatId);

        when(chatService.findChatById(chatId)).thenReturn(Optional.of(chat));
        when(chatService.createChat(chat)).thenReturn(chat);

//        chatController.handleChatMessage(chatId, messageRequest);

        verify(simpMessagingTemplate, times(1)).convertAndSend(anyString(), (Object) any());
    }

    @Test
    void testFetchSubscribeOnChatMessages() {
        Long chatId = 1L;
        Chat chat = new Chat();
        chat.setId(chatId);

        when(chatService.findChatById(chatId)).thenReturn(Optional.of(chat));

//        chatController.fetchSubscribeOnChatMessages(chatId);

        verify(simpMessagingTemplate, times(1)).convertAndSend(anyString(), (Object) any());
    }
}
