package com.example.movieapi.web.rest;


import com.example.movieapi.dto.JoinDTO;
import com.example.movieapi.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public  JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){
        joinService.joinProcess(joinDTO);
        return "ok";
    }

    @PostMapping("/find")
    public Integer findIdProcess(@RequestParam("username") String username) {
        Integer userId = joinService.findId(username);
        return userId;
    }
}
