package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.SaveUserRequest;
import com.library.study.demo.controller.dto.SaveUserResponse;
import com.library.study.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<SaveUserResponse> saveUser(@Valid @RequestBody SaveUserRequest userDto) {

        Long result = userService.save(userDto.toEntity());

        return ResponseEntity.ok(new SaveUserResponse(result));
    }
}
