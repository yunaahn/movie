package com.example.movieapi.chat.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Room implements Serializable {
    private String roomId;
    private String name;

    public Room(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }

    public static com.example.movieapi.dto.ChatRoom of(String name) {
        return new com.example.movieapi.dto.ChatRoom(name);
    }
}
