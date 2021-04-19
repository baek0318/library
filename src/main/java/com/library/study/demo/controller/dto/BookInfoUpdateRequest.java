package com.library.study.demo.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInfoUpdateRequest {

    private String title;

    public BookInfoUpdateRequest(String title) {
        this.title = title;
    }
}
