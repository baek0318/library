package com.library.study.demo.book.dto;

import com.library.study.demo.book.Book;
import lombok.*;

public class BookDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String title;
        private String author;
        private String ISBN;

        @Builder
        public Request(String title, String author, String ISBN) {
            this.title = title;
            this.author = author;
            this.ISBN = ISBN;
        }

        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .author(author)
                    .ISBN(ISBN)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private String title;
        private String author;
        private String ISBN;
        private Long libraryId;

        @Builder
        public Response(Long id, String title, String author, String ISBN) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.ISBN = ISBN;
        }

    }
}