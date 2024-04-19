package com.example.movieapi.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //브로커를 통한 메시지 전송 설정
        registry.enableSimpleBroker("/sub"); //sub으로 시작하는 목적지를 구독할 수 잇음
        registry.setApplicationDestinationPrefixes("/pub"); //발행
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp") //클라 - 웹소켓 접속하는 엔드포인트
                .withSockJS();
    }
}
