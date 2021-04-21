package com.library.study.demo.controller.dto.user;

import com.library.study.demo.domain.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveUserRequest {

    @Email
    private String email;

    @Size(min = 4)
    private String password;

    public SaveUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return new User(
                this.email,
                this.password,
                null
        );
    }
}
