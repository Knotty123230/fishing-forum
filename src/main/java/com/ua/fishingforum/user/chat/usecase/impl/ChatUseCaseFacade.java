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
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class ChatUseCaseFacade implements ChatUseCase {
    private static final String EXCEPTION_MESSAGE = "чат з айді %s не знайдено";
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
    public List<MessageResponse> handleChatMessage(Map<String, Object> headers, Long chatId) {
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
                orElseThrow(() -> new CustomException("Користувач з ім'ям %s ще не приєднався до жодного чату".formatted(userProfile.getNickname())));
        return chats.stream()
                .map(this.chatToChatResponseMapper::map)
                .toList();
    }

    @Override
    public void fetchJoinChat(Long chatId) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Chat chat = this.chatService.findChatById(chatId).orElseThrow(() -> new CustomException("Чату з id %d не існує".formatted(chatId)));
        chat.getMembers().add(userProfile);
        this.chatService.updateChat(chat);
        simpMessagingTemplate.convertAndSend("/chat/{chatId}/new-member".replace(
                        "{chatId}",
                        chat.getId().toString())
                , new UserProfileResponse(
                        userProfile.getNickname(),
                        userProfile.getImageLink())
        );
    }

    @Override
    public MessageResponse fetchJoinChatMemberAndSendMessage(Long chatId, UserProfileResponse joinedMember) {
        Chat chat = this.chatService.findChatById(chatId).orElseThrow(() -> new CustomException("чат з id %d не знайдено".formatted(chatId)));
        Message message = Message.builder()
                .chat(chat)
                .content("користувач %s приєднався до чату".formatted(joinedMember.nickname()))
                .build();
        Message createdMessage = this.messageService.save(message);
        return this.messageToMessageResponseMapper.map(createdMessage);
    }
}
