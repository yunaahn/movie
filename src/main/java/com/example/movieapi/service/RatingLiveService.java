package com.example.movieapi.service;

import com.example.movieapi.dto.ResponseRatingDTO;
import com.example.movieapi.web.rest.RedisController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RatingLiveService {
    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<ResponseRatingDTO> getRating(Long movieId){
        //logger.info("rating" + rating.getId());
        String key = "rating:" + movieId; // 각 영화별로 rating이 저장된 레디스 키
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = zSetOperations.rangeWithScores(key, 0, -1);
        logger.info("typedTuples" + typedTuples);
        List<ResponseRatingDTO> ratings = new ArrayList<>();
        for (ZSetOperations.TypedTuple<Object> tuple : typedTuples) {
            String userId = (String) tuple.getValue(); // 사용자 ID
            Double rating = tuple.getScore(); // 영화에 대한 평점
            // 여기서 사용자 ID와 평점을 사용하여 필요한 작업을 수행하여 ResponseRatingDTO를 생성합니다.
            // 이 예시에서는 단순히 DTO를 생성하고 리스트에 추가합니다.
            ResponseRatingDTO ratingDTO = new ResponseRatingDTO();
            ratingDTO.setId(movieId); // 사용자 ID를 Long 형으로 변환하여 설정
            ratingDTO.setRating(rating.intValue()); // 평점을 Integer 형으로 변환하여 설정

            logger.info("ratingDTO" + ratingDTO);
            ratings.add(ratingDTO);
        }
        return ratings;
    }
}
