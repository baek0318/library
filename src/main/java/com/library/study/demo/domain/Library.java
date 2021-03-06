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
    private ArrayList<Book> bookList;
    private ArrayList<Book> borrowedBookList;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
