package com.example.movieapi.repository;

import com.example.movieapi.dto.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRepository {

    private  Map<String, ChatRoom> chatRooms;

    public ChatRepository(Map<String, ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public void save(String roomId, ChatRoom chatRoom) {
        chatRooms.put(roomId, chatRoom);
    }

    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    public List<ChatRoom> findAll() {
        return new ArrayList<>(chatRooms.values());
    }
}