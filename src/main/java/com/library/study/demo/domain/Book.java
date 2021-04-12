package com.library.study.demo.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @ManyToOne
    @JoinColumn(name = "bookInfoId", referencedColumnName = "id")
    private BookInfo info;

    protected Book() {}

    public Book(BookInfo info) {
        this.info = info;
    }

    public boolean isAvailable() {
//        for(Borrow borrow : borrows) {
//            if(borrow.getEndDate() == null) {
//                return false;
//            }
//        }
        return false;
    }
}
