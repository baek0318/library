package com.library.study.demo.borrow.dto;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

public class BorrowDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        private Long bookId;
        private Long libraryId;

        @Builder
        public Request(Long bookId, Long libraryId) {
            this.bookId = bookId;
            this.libraryId = libraryId;
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private Date borrowDate;
        private BookDto.Response book;
        private UserDto.Response user;

        @Builder
        public Response(Long id, Date borrowDate, BookDto.Response book, UserDto.Response user) {
            this.id = id;
            this.borrowDate = borrowDate;
            this.book = book;
            this.user = user;
        }

    }
}
