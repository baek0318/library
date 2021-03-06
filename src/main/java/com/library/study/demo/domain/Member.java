package com.library.study.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="member")
@Getter
@NoArgsConstructor
public class Member implements User{
    @Id
    public String id;

    public String password;
    public String name;

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
