package com.library.study.demo.controller.dto.borrow;

import com.library.study.demo.domain.Borrow;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowInfoResponse {

    private LocalDate borrowDate;

    private LocalDate returnDate;

    public BorrowInfoResponse(Borrow borrow) {
        this.borrowDate = borrow.getBorrowDate();
        this.returnDate = borrow.getReturnDate();
    }
}
