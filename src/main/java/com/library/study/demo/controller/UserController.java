package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.user.SaveUserRequest;
import com.library.study.demo.controller.dto.user.SaveUserResponse;
import com.library.study.demo.controller.dto.user.UserResponse;
import com.library.study.demo.domain.User;
import com.library.study.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{member-id}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable(name = "member-id") Long id) {

        User result = userService.findById(id);

        return ResponseEntity.ok(new UserResponse(result.getEmail()));
    }
}
