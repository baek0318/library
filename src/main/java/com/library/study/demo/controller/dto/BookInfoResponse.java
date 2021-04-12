package com.library.study.demo.controller.dto;

import com.library.study.demo.domain.BookInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookInfoResponse {

    private Long id;

    private String title;

    private AuthorResponse authorResponse;

    public BookInfoResponse(Long id, String title, AuthorResponse authorResponse) {
        this.id = id;
        this.title = title;
        this.authorResponse = authorResponse;
    }

    public BookInfoResponse(BookInfo bookInfo) {
        this.id = bookInfo.getId();
        this.title = bookInfo.getTitle();
        this.authorResponse = new AuthorResponse(bookInfo.getAuthor());
    }

}
