package com.library.study.demo.domain;

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
    @Column(name="USER_ID",nullable = false)
    private Long id;

    @Column(nullable=false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<Book>();

    @Builder
    public User(String userId, String password, Role role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }
}
