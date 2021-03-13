package com.library.study.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
public class Library {

    private ArrayList<Book> bookList; // 가지고 있는 책 list
    private ArrayList<Book> borrowedBookList; // 빌려준 책 list


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
