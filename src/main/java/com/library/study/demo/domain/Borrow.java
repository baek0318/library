package com.library.study.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate borrowDate;

    @Column
    private LocalDate returnDate;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Borrow(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public void setBorrowDate(LocalDate date) {
        this.borrowDate = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void updateReturnDate(LocalDate date) {
        this.returnDate = date;
    }
}
