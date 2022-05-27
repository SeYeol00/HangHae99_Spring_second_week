package com.sparta.myblog.controller;

import com.sparta.myblog.domain.SignupRequestDto;
import com.sparta.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;


    @Autowired // DI 디펜던시 인젝션
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {

        return "login";//로그인 페이지
    }

    @GetMapping("/user/login")//동적 리스폰스로 프론트에사ㅓ 타임리프로 받는다는 가정
    public String loginError(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";//회원가입페이지
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";//로그인 페이지로 리다이렉트
    }


}

