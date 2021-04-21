package com.library.study.demo.controller.dto.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private String email;

    public UserResponse(String email) {
        this.email = email;
    }
}
