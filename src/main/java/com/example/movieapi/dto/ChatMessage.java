package com.example.movieapi.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    public enum MessageType {
        ENTER, JOIN, TALK
    }
    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;

}
