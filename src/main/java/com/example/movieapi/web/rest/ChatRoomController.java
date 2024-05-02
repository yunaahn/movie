package com.example.movieapi.web.rest;

import com.example.movieapi.configuration.RedisPublisher;
import com.example.movieapi.dto.ChatRoom;
import com.example.movieapi.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisPublisher messagePublisher;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ChatRoomService chatRoomService;

    @GetMapping("/chat/rooms-all")
    public String getAllRooms() {
        return "/chat/home";
    }

    @GetMapping("/chat/rooms")
    @ResponseBody
    public List<ChatRoom> getRooms() {
        return chatRoomService.findAll();
    }

    @PostMapping("/chat/room")
    public String createRoom(@RequestParam("name") String name) {
        chatRoomService.createRoom(name);
        return "redirect:/chat/rooms";
    }

    @GetMapping("/chat/room/{roomId}")
    @ResponseBody
    public ChatRoom getRoom(@PathVariable("roomId")  String roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}
