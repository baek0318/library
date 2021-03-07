package com.library.study.demo.controller;

import com.library.study.demo.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @PostMapping("/signin")
    public Long login() {
        return 10L;
    }

    @PostMapping("/signup")
    public UserDto.SignupRes join(){
        return new UserDto.SignupRes(1L);
    }
}
