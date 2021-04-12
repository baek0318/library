package com.library.study.demo.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BookInfoListResponse {

    private List<BookInfoResponse> bookInfoResponseList;

    public BookInfoListResponse(List<BookInfoResponse> bookInfoResponseList) {
        this.bookInfoResponseList = bookInfoResponseList;
    }
}
