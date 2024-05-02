package com.example.movieapi.web.rest;


import com.example.movieapi.chat.model.Message;
import com.example.movieapi.configuration.RedisPublisher;
import com.example.movieapi.configuration.RedisSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisPublisher messagePublisher;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/publish")
    public void publish( @RequestBody Message message) {
        //logger.info("publishing : " + message.toString());

        // 각 채팅방에 대한 토픽 생성
        //ChannelTopic topic = new ChannelTopic("chatRoom.");
        //RedisPublisher publisher = new RedisPublisher(redisTemplate, topic);

        //logger.info("roomId : " + roomId);
        //logger.info("topic : " + topic);

        messagePublisher.publish(message.toString());
    }

    @GetMapping("/subscribe")
    public List<String> getMessages(){
        return RedisSubscriber.messageList;
    }
}
