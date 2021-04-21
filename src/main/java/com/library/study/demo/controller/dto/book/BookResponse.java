package com.library.study.demo.controller.dto.book;

import com.library.study.demo.domain.Book;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponse {

    private Long id;

    private Boolean status;

    public BookResponse(Long id, Boolean status) {
        this.id = id;
        this.status = status;
    }

    public BookResponse(Book book) {
        this.id = book.getId();
        this.status = book.getStatus();
    }
}
