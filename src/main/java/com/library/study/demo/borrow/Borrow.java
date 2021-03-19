package com.library.study.demo.borrow;

import com.library.study.demo.book.Book;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Borrow {

    @Id
    @GeneratedValue
    @Column(name = "BORROW_ID", nullable = false)
    private Long id;

    @CreationTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date borrowDate;

//    @Temporal(value=TemporalType.TIMESTAMP)
//    private Date dueDate;

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "LIBRARY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Library library;

    @JoinColumn(name = "BOOK_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Book book;

    public Borrow(User user, Library library, Book book) {
        this.user = user;
        this.library = library;
        this.book = book;

        user.addBorrow(this);
        library.addBorrow(this);
    }

    public BorrowDto.Response toResponseDto() {
        return BorrowDto.Response.
                builder()
                .id(id)
                .borrowDate(borrowDate)
                .book(book.toResponseDto())
                .user(user.toResponseDto())
                .build();
    }
}
