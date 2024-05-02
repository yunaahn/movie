package com.example.movieapi.web.rest;


import com.example.movieapi.chat.model.Message;
import com.example.movieapi.configuration.RedisPublisher;
import com.example.movieapi.configuration.RedisSubscriber;
import com.example.movieapi.dto.ResponseRatingDTO;
import com.example.movieapi.entity.Rating;
import com.example.movieapi.service.RatingLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RedisPublisher messagePublisher;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RatingLiveService ratingLiveService;

    //평점 메김
    @PostMapping("/update")
    public void publish( @RequestBody Rating rating) {
        messagePublisher.publish(rating.toString());
    }
    //평점 리스트
    @GetMapping("/list")
    public List<String> getMessages(){
        return RedisSubscriber.messageList;
    }

    //db에서 저장되는 값이 들옴 - > 레디스 저장값으로 수정하고싶음
    @GetMapping("/list/{movieId}")
    public ResponseEntity<List<ResponseRatingDTO>> getRatingList(@PathVariable Long movieId) {
        List<ResponseRatingDTO> ratingList = ratingLiveService.getRating(movieId);
        return ResponseEntity.ok(ratingList);
    }


}
