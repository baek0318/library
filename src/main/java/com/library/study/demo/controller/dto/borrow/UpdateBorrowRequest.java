package com.library.study.demo.controller.dto.borrow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateBorrowRequest {

    private String date;

    public UpdateBorrowRequest(String date) {
        this.date = date;
    }
}
