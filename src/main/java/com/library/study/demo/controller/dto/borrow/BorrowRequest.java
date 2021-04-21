package com.library.study.demo.controller.dto.borrow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowRequest {

    private String date;

    private Long bookId;

    public BorrowRequest(String date, Long bookId) {
        this.date = date;
        this.bookId = bookId;
    }
}
