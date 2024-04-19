package com.example.movieapi.service;

import com.example.movieapi.configuration.RedisSubscriber;
import com.example.movieapi.dto.ChatRoom;
import com.example.movieapi.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final RedisSubscriber redisSubscriber;
    private final RedisMessageListenerContainer redisMessageListenerContainer;

    private final Map<String, ChannelTopic> topics = new HashMap<>();

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRoomRepository.findById(roomId);
    }

    public void createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.of(name);
        chatRoomRepository.save(chatRoom);
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);
        }
    }
}