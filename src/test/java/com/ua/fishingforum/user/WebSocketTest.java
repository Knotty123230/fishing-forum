package com.ua.fishingforum.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.fishingforum.security.web.dto.AccessToken;
import com.ua.fishingforum.security.web.dto.LoginRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.ChatResponse;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Log4j2
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WebSocketTest {


    private static WebClient client;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;
    @Value("${local.server.port}")
    private int port;

    @NotNull
    private CompletableFuture<List<MessageResponse>> getMessageResponseCompletableFuture(String sendUrl, String subscribeUrl, MessageRequest messageRequest) throws InterruptedException {
        StompSession stompSession = client.getStompSession();

        stompSession.send(sendUrl, messageRequest);
        Thread.sleep(2000);
        CompletableFuture<List<MessageResponse>> messageFuture = new CompletableFuture<>();
        TypeReference<List<MessageResponse>> typeReference = new TypeReference<>() {
        };
        stompSession.subscribe(subscribeUrl, new RunStopFrameHandler<>(messageFuture, typeReference, mapper));
        return messageFuture;
    }


    @BeforeEach
    public void setup() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String loginUrl = "http://localhost:" + port + "/api/v1/authentication/access_token";
        LoginRequest loginRequest = new LoginRequest("1dawdwadwa@gmail.com", "password");

        AccessToken accessToken = restTemplate.postForObject(loginUrl, loginRequest, AccessToken.class);
        assert accessToken != null;
        String token = accessToken.idToken();
        String wsUrl = "ws://localhost:" + port + "/ws";

        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("Authorization", "Bearer " + token);
        StompHeaders connectHeaders = new StompHeaders();
        connectHeaders.add("Authorization", "Bearer " + token);
        StompSession stompSession = stompClient.connectAsync(wsUrl, handshakeHeaders, connectHeaders, new MyStompSessionHandler())
                .get(5, TimeUnit.SECONDS);

        client = WebClient.builder()
                .stompClient(stompClient)
                .stompSession(stompSession)
                .build();
    }

    @AfterEach
    public void tearDown() {

        if (client.getStompSession().isConnected()) {
            client.getStompSession().disconnect();
            client.getStompClient().stop();
        }
    }

    @Test
    @Order(1)
    void should_PassSuccessfully_When_CreateChat() throws Exception {
        long chatId = 1L;
        MessageRequest messageRequest = new MessageRequest("Test message");

        CompletableFuture<List<MessageResponse>> messageFuture = getMessageResponseCompletableFuture(
                "/fishing-forum/chat/" + chatId + "/message",
                "/fishing-forum/chat/" + chatId + "/messages",
                messageRequest);

        List<MessageResponse> messageResponse = messageFuture.get(10, TimeUnit.SECONDS);
        Assertions.assertNotNull(messageResponse);
        Assertions.assertNotNull(messageResponse.get(0).id());
        Assertions.assertEquals(messageRequest.content(), messageResponse.get(0).content());
        Assertions.assertNotNull(messageResponse.get(0).fromMessage());
        Assertions.assertNotNull(messageResponse.get(0).fromMessage().nickname());
        Assertions.assertNotNull(messageResponse.get(0).createdAt());
    }

    @Test
    @Order(2)
    void should_successfully_join_chat() throws ExecutionException, InterruptedException, TimeoutException {
        long chatId = 1L;

        StompSession stompSession = client.getStompSession();
        MessageRequest messageRequest = new MessageRequest("Test message1");
        stompSession.send("/fishing-forum/chat/join/" + chatId, messageRequest);

        CompletableFuture<ChatResponse> messageFuture = new CompletableFuture<>();
        stompSession.subscribe("/fishing-forum/chat/" + chatId + "/new-member", new StompFrameHandler() {
            @NotNull
            @Override
            public Type getPayloadType(@NotNull StompHeaders headers) {
                return ChatResponse.class;
            }

            @Override
            public void handleFrame(@NotNull StompHeaders headers, Object payload) {
                messageFuture.complete((ChatResponse) payload);
            }
        });

        ChatResponse chatResponse = messageFuture.get(5, TimeUnit.SECONDS);

        Assertions.assertNotNull(chatResponse);
    }


    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);

        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        return transports;
    }

    @Data
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class RunStopFrameHandler<T> implements StompFrameHandler {
        CompletableFuture<T> future;
        TypeReference<T> typeReference;
        @Autowired
        ObjectMapper objectMapper;

        @NotNull
        @Override
        public Type getPayloadType(@NotNull StompHeaders stompHeaders) {
            return typeReference.getType();
        }

        @Override
        public void handleFrame(@NotNull StompHeaders stompHeaders, Object o) {
            T messageResponse = objectMapper.convertValue(o, typeReference);
            future.complete(messageResponse);
        }

    }
    private static class MyStompSessionHandler implements StompSessionHandler{

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            throw new RuntimeException("Failure in WebSocket handling", exception);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {

        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return null;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {

        }
    }

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class WebClient {

        WebSocketStompClient stompClient;

        StompSession stompSession;
    }
}