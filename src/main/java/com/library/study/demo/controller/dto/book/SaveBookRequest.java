package com.library.study.demo.controller.dto.book;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveBookRequest {

    private Long bookInfoId;

    public SaveBookRequest(Long bookInfoId) {
        this.bookInfoId = bookInfoId;
    }
}
