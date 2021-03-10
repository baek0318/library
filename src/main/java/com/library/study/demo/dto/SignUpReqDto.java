package com.library.study.demo.dto;

import com.library.study.demo.domain.Role;
import com.library.study.demo.domain.User;
import lombok.*;

import static com.library.study.demo.domain.Role.USER;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReqDto {
    private String loginid;
    private String password;
    private Role role = USER;

    @Builder
    SignUpReqDto(String loginid, String password, Role role){
        this.loginid=loginid;
        this.password=password;
        this.role=role;
    }

    public User toEntity(){
        return User.builder()
                .loginid(loginid)
                .password(password)
                .role(role)
                .build();
    }
}
