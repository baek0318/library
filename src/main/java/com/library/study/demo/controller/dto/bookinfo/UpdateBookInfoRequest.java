package com.library.study.demo.controller.dto.bookinfo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateBookInfoRequest {

    private String title;

    public UpdateBookInfoRequest(String title) {
        this.title = title;
    }
}
