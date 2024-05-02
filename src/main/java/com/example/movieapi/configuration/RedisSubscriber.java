package com.example.movieapi.configuration;

import com.example.movieapi.dto.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RedisSubscriber implements MessageListener {
//    private final ObjectMapper objectMapper;
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final SimpMessageSendingOperations messageTemplate;

    public static List<String> messageList = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        messageList.add(message.toString());
        System.out.println("Message received" + message.toString());
    }

    public List<String> getmessageList() {
        return messageList;
    }


//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        try {
//            String publishMessage = redisTemplate
//                    .getStringSerializer()
//                    .deserialize(message.getBody());
//
//            ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
//            messageTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
