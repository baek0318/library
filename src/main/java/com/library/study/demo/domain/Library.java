package com.library.study.demo.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Library {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private List<Book> Books = new ArrayList<Book>();
}
