package com.library.study.demo.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    protected Author() {}

    public Author(String name) {
        this.name = name;
    }
}

//@Entity
//@Getter
//@Setter
//public class AuthorWithBookInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String name;
//
//    @OneToMany(mappedBy = "d")
//    private List<BookInfo> bookInfoList;
//
//    protected  Author() {}
//
//    public Author(String name) {
//        this.name = name;
//    }
//}
