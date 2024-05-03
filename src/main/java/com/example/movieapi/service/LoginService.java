package com.example.movieapi.service;


import com.example.movieapi.entity.Member;
import com.example.movieapi.repository.MemberRepository;
import com.example.movieapi.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId)
                .stream().filter(m -> m.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }


}
