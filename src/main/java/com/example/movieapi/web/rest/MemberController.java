package com.example.movieapi.web.rest;


import ch.qos.logback.core.model.Model;
import com.example.movieapi.entity.Member;
import com.example.movieapi.repository.MemberRepository;
import com.example.movieapi.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid Member member, BindingResult result) {

        if(result.hasErrors()) {
            return "members/addMemberForm";
        }

        memberService.join(member);
        return "redirect:/";
    }
}
