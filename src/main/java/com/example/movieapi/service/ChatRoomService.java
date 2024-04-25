package com.example.movieapi.service;

import com.example.movieapi.configuration.RedisSubscriber;
import com.example.movieapi.dto.ChatRoom;
import com.example.movieapi.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ChatRoomService {
    private  ChatRoomRepository chatRoomRepository;
    // #. subscribe
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisTemplate<String, Object> redisTemplate;
    private Map<String, ChannelTopic> topics;

    @Autowired
    public ChatRoomService(RedisMessageListenerContainer redisMessageListenerContainer,
                           RedisTemplate<String, Object> redisTemplate, ChatRoomRepository chatRoomRepository) {
        this.redisMessageListenerContainer = redisMessageListenerContainer;
        this.redisTemplate = redisTemplate;
        this.chatRoomRepository = chatRoomRepository;


    }

    private static final Logger logger = LoggerFactory.getLogger(ChatRoomService.class);



    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId);
    }

    public void createRoom(String name) {
        logger.debug("Saving name: {}", name);
        ChatRoom chatRoom = ChatRoom.of(name);
        logger.debug("before Saving chatRoom: {}", chatRoom);
        chatRoomRepository.save(chatRoom);
        redisTemplate.opsForHash().put("CHAT_ROOMS", chatRoom.getRoomId(), chatRoom);
        logger.debug("after Saving chatRoom: {}", chatRoom);
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            // TODO: Redis에 메시지 수신을 처리할 메서드를 정의하고 MessageListenerAdapter를 사용하여 등록
            // redisMessageListenerContainer.addMessageListener(new MessageListenerAdapter(), topic);
            topics.put(roomId, topic);
        }
    }
}