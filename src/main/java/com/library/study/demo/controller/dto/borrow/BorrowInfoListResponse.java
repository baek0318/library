package com.library.study.demo.controller.dto.borrow;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowInfoListResponse {

    private List<BorrowInfoResponse> borrowInfoResponses;

    public BorrowInfoListResponse(List<BorrowInfoResponse> borrowInfoResponses) {
        this.borrowInfoResponses = borrowInfoResponses;
    }
}
