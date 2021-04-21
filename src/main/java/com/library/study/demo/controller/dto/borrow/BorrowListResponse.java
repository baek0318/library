package com.library.study.demo.controller.dto.borrow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowListResponse {

    private List<BorrowResponse> borrowInfoList;

    public BorrowListResponse(List<BorrowResponse> borrowInfoList) {
        this.borrowInfoList = borrowInfoList;
    }
}
