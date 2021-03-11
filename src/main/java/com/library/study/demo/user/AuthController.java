package com.library.study.demo.user;

import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public Long login() {
        return 10L;
    }

    @PostMapping("/signup")
    public ResponseEntity join(@RequestBody SignUpReqDto signUpReqDto){
        SignUpResDto signUpResDto = authService.signUp(signUpReqDto);
        return ResponseEntity
                .created(URI.create("/users/"+signUpResDto.getId()))
                .body(signUpResDto);
    }
}
