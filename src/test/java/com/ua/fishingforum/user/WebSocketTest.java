package com.ua.fishingforum.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ua.fishingforum.security.web.dto.AccessToken;
import com.ua.fishingforum.security.web.dto.LoginRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageRequest;
import com.ua.fishingforum.user.chat.web.controller.dto.MessageResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
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
import java.util.concurrent.TimeUnit;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Log4j2
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class WebSocketTest {


    private static WebClient client;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;
    @Value("${local.server.port}")
    private int port;

    @NotNull
    private CompletableFuture<List<MessageResponse>> getMessageResponseCompletableFuture(Long chatId, MessageRequest messageRequest) {
        StompSession stompSession = client.getStompSession();

        stompSession.send("/fishing-forum/chat/" + chatId + "/message", messageRequest);

        // Wait for response
        CompletableFuture<List<MessageResponse>> messageFuture = new CompletableFuture<>();
        stompSession.subscribe("/fishing-forum/chat/" + chatId + "/messages", new StompFrameHandler() {

            @NotNull
            @Override
            public Type getPayloadType(@NotNull StompHeaders stompHeaders) {
                return List.class;
            }

            @Override
            public void handleFrame(@NonNull StompHeaders stompHeaders, Object o) {
                List<MessageResponse> messageResponses = mapper.convertValue(o, new TypeReference<>() {
                });

                messageFuture.complete(messageResponses);
            }
        });
        return messageFuture;
    }


    @BeforeAll
    public void setup() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String loginUrl = "http://localhost:" + port + "/api/v1/authentication/access_token";
        LoginRequest loginRequest = new LoginRequest("1dawdwadwa@gmail.com", "password");

        AccessToken accessToken = restTemplate.postForObject(loginUrl, loginRequest, AccessToken.class);
        assert accessToken != null;
        String token = accessToken.idToken();
        RunStopFrameHandler runStopFrameHandler = new RunStopFrameHandler(new CompletableFuture<>());

        String wsUrl = "ws://localhost:" + port + "/ws";

        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("Authorization", "Bearer " + token);
        StompHeaders connectHeaders = new StompHeaders();
        connectHeaders.add("Authorization", "Bearer " + token);
        StompSession stompSession = stompClient.connectAsync(wsUrl, handshakeHeaders, connectHeaders, new StompSessionHandlerAdapter() {
                })
                .get(5, TimeUnit.SECONDS);

        client = WebClient.builder()
                .stompClient(stompClient)
                .stompSession(stompSession)
                .build();
    }

    @AfterAll
    public void tearDown() {

        if (client.getStompSession().isConnected()) {
            client.getStompSession().disconnect();
            client.getStompClient().stop();
        }
    }

    @Test
    @WithMockUser
    void should_PassSuccessfully_When_CreateChat() throws Exception {
        Long chatId = 1L;
        MessageRequest messageRequest = new MessageRequest("Test message");

        CompletableFuture<List<MessageResponse>> messageFuture = getMessageResponseCompletableFuture(chatId, messageRequest);

        List<MessageResponse> messageResponse = messageFuture.get(5, TimeUnit.SECONDS);
        Assertions.assertNotNull(messageResponse);
        Assertions.assertNotNull(messageResponse.get(0).id());
        Assertions.assertEquals(messageRequest.content(), messageResponse.get(0).content());
        Assertions.assertNotNull(messageResponse.get(0).fromMessage());
        Assertions.assertNotNull(messageResponse.get(0).fromMessage().nickname());
        Assertions.assertNotNull(messageResponse.get(0).createdAt());
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);

        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        return transports;
    }

    @Data
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class RunStopFrameHandler implements StompFrameHandler {

        CompletableFuture<Object> future;

        @Override
        public @NonNull Type getPayloadType(StompHeaders stompHeaders) {

            log.info(stompHeaders.toString());

            return byte[].class;
        }

        @Override
        public void handleFrame(@NonNull StompHeaders stompHeaders, Object o) {

            log.info(o);

            future.complete(o);

            future = new CompletableFuture<>();
        }
    }

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class WebClient {

        WebSocketStompClient stompClient;

        StompSession stompSession;

        String sessionToken;

        RunStopFrameHandler handler;

    }
}