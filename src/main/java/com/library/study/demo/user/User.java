package com.library.study.demo.user;

import com.library.study.demo.book.Book;
import com.library.study.demo.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<Book>();

    @Builder
    public User(String loginId, String password, String name, Role role) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UserDto.Response toResponseDto() {
        return UserDto.Response.builder()
                .id(id)
                .name(name)
                .loginId(loginId)
                .build();
    }
}
