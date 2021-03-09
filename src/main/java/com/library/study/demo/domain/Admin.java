package com.library.study.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User{

    public Admin(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
