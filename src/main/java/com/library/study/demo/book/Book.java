package com.library.study.demo.book;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.Library;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String isbn;

    @JoinColumn(name = "LIBRARY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Library library;

    @Enumerated(value = EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;

    @Builder
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public BookDto.Response toResponseDto() {
        return BookDto.Response.builder()
                .id(id)
                .title(title)
                .author(author)
                .isbn(isbn)
                .status(status)
                .library(library.toResponseDto())
                .build();
    }

    public void register(Library library) {
        if (this.library != null) {
            this.library.getBooks().remove(this);
        }
        this.library = library;
    }

}
