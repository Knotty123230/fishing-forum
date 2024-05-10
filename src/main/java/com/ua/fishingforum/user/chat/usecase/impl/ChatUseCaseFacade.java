package com.ua.fishingforum.user.chat.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.chat.mapper.ChatToChatResponseMapper;
import com.ua.fishingforum.user.chat.mapper.MessageToMessageResponseMapper;
import com.ua.fishingforum.user.chat.model.Chat;
import com.ua.fishingforum.user.chat.model.Message;
import com.ua.fishingforum.user.chat.service.ChatService;
import com.ua.fishingforum.user.chat.service.MessageService;
import com.ua.fishingforum.user.chat.usecase.ChatUseCase;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatResponse;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import com.ua.fishingforum.user.profile.web.dto.UserProfileResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.ua.fishingforum.user.chat.web.controller.ChatController.FETCH_JOIN_CHAT_SUBSCRIBE;


@Component
@RequiredArgsConstructor
public class ChatUseCaseFacade implements ChatUseCase {
    private static final String EXCEPTION_MESSAGE = "чат з айді %s не знайдено";
    private static final String EXCEPTION_MESSAGE_NOT_JOIN_ANY_CHAT = "Користувач з ім'ям %s ще не приєднався до жодного чату";
    private static final String JOIN_CHAT_SUCCESSFUL = "користувач %s приєднався до чату";
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final MessageToMessageResponseMapper messageToMessageResponseMapper;
    private final ChatToChatResponseMapper chatToChatResponseMapper;

    @Override
    public void createChat(Map<String, Object> headers, ChatRequest chatRequest) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Chat chat = Chat.builder()
                .members(List.of(userProfile))
                .name(chatRequest.name())
                .build();
        Chat createdChat = chatService.createChat(chat);
        ChatResponse chatResponse = new ChatResponse(
                createdChat.getId(),
                createdChat.getName(),
                createdChat.getMembers()
                        .stream()
                        .map(profile -> new UserProfileResponse(profile.getNickname(), profile.getImageLink()))
                        .toList(),
                createdChat.getMessages()
                        .stream()
                        .map(messageToMessageResponseMapper::map)
                        .toList()
        );
        simpMessagingTemplate.convertAndSend("/chats", chatResponse);

    }

    @Override
    public void handleMessage(Map<String, Object> headers, Long chatId, MessageRequest messageRequest) {
        UserProfile currentUserProfile = currentUserProfileApiService.currentUserProfile();
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        Message message = Message
                .builder()
                .from(currentUserProfile)
                .chat(chat)
                .content(messageRequest.content())
                .build();
        Message savedMessage = messageService.save(message);
        simpMessagingTemplate.convertAndSend(
                "/chat/" + chat.getId() + "/messages",
                messageToMessageResponseMapper.map(savedMessage)
        );
    }

    @Override
    public List<MessageResponse> handleSubscribeChatMessage(Map<String, Object> headers, Long chatId) {
        Chat chat = chatService.findChatById(chatId)
                .orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        return chat.getMessages().stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .limit(20)
                .map(messageToMessageResponseMapper::map)
                .toList();
    }

    @Override
    public List<ChatResponse> fetchAllChatsForCurrentUser() {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        List<Chat> chats = this.chatService.findAllChatsForCurrentUser(userProfile).
                orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE_NOT_JOIN_ANY_CHAT.formatted(userProfile.getNickname())));
        return chats.stream()
                .map(this.chatToChatResponseMapper::map)
                .toList();
    }

    @Override
    @Transactional
    public void fetchJoinChat(Map<String, Object> headers, Long chatId, MessageRequest messageRequest) {

        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Chat chat = this.chatService.findChatById(chatId).orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        Message build = Message.builder()
                .content(messageRequest.content())
                .chat(chat)
                .build();
        Message message = this.messageService.save(build);
        chat.getMembers().add(userProfile);
        chat.getMessages().add(message);
        this.chatService.updateChat(chat);

        String url = FETCH_JOIN_CHAT_SUBSCRIBE.replace(
                "{chatId}",
                chat.getId().toString());
        simpMessagingTemplate.convertAndSend(
                url,
                new UserProfileResponse(
                        userProfile.getNickname(),
                        userProfile.getImageLink())
        );
    }

    @Override
    public ChatResponse fetchJoinChatMemberAndSendMessage(Long chatId) {
        Chat chat = this.chatService.findChatById(chatId).orElseThrow(() -> new CustomException(EXCEPTION_MESSAGE.formatted(chatId)));
        return this.chatToChatResponseMapper.map(chat);
    }
}
