package com.library.study.demo.controller.dto.borrow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowResponse {

    private Long id;

    public BorrowResponse(Long id) {
        this.id = id;
    }
}
