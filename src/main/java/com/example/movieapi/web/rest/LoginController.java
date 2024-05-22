package com.example.movieapi.web.rest;


import com.example.movieapi.entity.LoginForm;
import com.example.movieapi.entity.Member;
import com.example.movieapi.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/loginpage")
    public String login() {
        return "/login/login"; // login.html 뷰를 반환
    }

//    @GetMapping("/login")
//    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
//        return "/login/loginForm";
//    }
//
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("loginForm") LoginForm form,
//                        BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "/login/loginForm";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//        log.info("loginMember: {}", loginMember);
//
//        if(loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
//            return "/login/loginForm";
//        }
//
//        return "redirect:/";
//    }


}


