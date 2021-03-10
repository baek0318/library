package com.library.study.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ID")
    private Long id;

    private String name;

    public Library(String name){
        this.name=name;
    }

    @OneToMany(mappedBy = "library",fetch = FetchType.LAZY)
    private List<Book> Books = new ArrayList<Book>();
}
