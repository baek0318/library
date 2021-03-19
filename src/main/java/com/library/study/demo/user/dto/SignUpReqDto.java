package com.library.study.demo.user.dto;

import com.library.study.demo.user.Role;
import com.library.study.demo.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static com.library.study.demo.user.Role.USER;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReqDto {
    @Length(min = 5, max = 20)
    private String loginId;
    @Length(min = 8, max = 20)
    private String password;
    private String name;
    private Role role = USER;

    @Builder
    public SignUpReqDto(String loginId, String password, String name, Role role) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .role(role)
                .build();
    }
}
