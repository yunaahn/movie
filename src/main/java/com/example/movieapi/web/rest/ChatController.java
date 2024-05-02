package com.example.movieapi.web.rest;

import com.example.movieapi.configuration.RedisPublisher;
import com.example.movieapi.dto.ChatMessage;
import com.example.movieapi.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final RedisPublisher redisPublisher;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessage message) {
        if (isJoin(message)) {
            chatRoomService.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
       // redisPublisher.publish(chatRoomService.getTopic(message.getRoomId()), message);
    }
    private boolean isJoin(final ChatMessage message) {
        return message.getMessageType().equals(ChatMessage.MessageType.JOIN);
    }


}