package com.library.study.demo.controller.dto.borrow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowRequest {

    private Long bookId;

    public BorrowRequest(Long bookId) {
        this.bookId = bookId;
    }
}
