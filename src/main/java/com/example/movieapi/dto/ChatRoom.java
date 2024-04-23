package com.example.movieapi.dto;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
public class ChatRoom implements Serializable{
    private String roomId;
    private String name;

    public ChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }

    public static ChatRoom of(String name) {
        return new ChatRoom(name);
    }
    
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    private String roomId;
//    private String name;
//    private final Set<WebSocketSession> sessions = new HashSet<>();
//
//    private ChatRoom(String roomId, String name) {
//        this.roomId = roomId;
//        this.name = name;
//    }
//
//    public void join(WebSocketSession session) {
//        sessions.add(session);
//    }
//
//    public void sendMessage(TextMessage message) {
//        this.getSessions()
//                .parallelStream()
//                .forEach(session -> sendMessageToSession(session, message));
//    }
//    private void sendMessageToSession(WebSocketSession session, TextMessage message) {
//        try {
//            session.sendMessage(message);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static class Builder {
//        private String roomId;
//        private String name;
//
//        public Builder roomId(String roomId) {
//            this.roomId = roomId;
//            return this;
//        }
//
//        public Builder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public ChatRoom build() {
//            // 필수 필드들의 값이 설정되었는지 확인하고 필요한 경우 예외 처리
//            if (roomId == null || name == null) {
//                throw new IllegalStateException("roomId and name must be set");
//            }
//
//            return new ChatRoom(roomId, name);
//        }
//    }
//
//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static ChatRoom of(String name, String s) {
//        return new Builder()
//                .name(name)
//                .roomId(UUID.randomUUID().toString())
//                .build();
//    }
}
