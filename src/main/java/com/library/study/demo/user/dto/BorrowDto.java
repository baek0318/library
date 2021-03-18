package com.library.study.demo.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BorrowDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private Long libraryId;
        private Long bookId;

        @Builder
        Request(Long libraryId, Long bookId) {
            this.libraryId = libraryId;
            this.bookId = bookId;
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {


        @Builder
        Response() {

        }
    }
}
