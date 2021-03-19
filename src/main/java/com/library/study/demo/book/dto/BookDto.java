package com.library.study.demo.book.dto;

import com.library.study.demo.book.Book;
import com.library.study.demo.book.BookStatus;
import com.library.study.demo.library.dto.LibraryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String title;
        private String author;
        private String isbn;

        @Builder
        public Request(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public Book toEntity() {
            return Book.builder()
                    .title(title)
                    .author(author)
                    .isbn(isbn)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private String title;
        private String author;
        private String isbn;
        private BookStatus status;
        private LibraryDto.Response library;

        @Builder
        public Response(Long id, String title, String author, BookStatus status, String isbn, LibraryDto.Response library) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.status = status;
            this.isbn = isbn;
            this.library = library;
        }

    }
}