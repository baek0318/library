package com.library.study.demo.user.dto;

import com.library.study.demo.user.Role;
import com.library.study.demo.user.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import static com.library.study.demo.user.Role.USER;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReqDto {
    @Length(min = 5,max = 20)
    private String loginid;
    @Length(min = 8,max = 20)
    private String password;
    private Role role = USER;

    @Builder
    public SignUpReqDto(String loginid, String password, Role role){
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
