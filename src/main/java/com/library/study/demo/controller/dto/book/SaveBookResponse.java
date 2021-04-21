package com.library.study.demo.controller.dto.book;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveBookResponse {

    private Long id;

    public SaveBookResponse(Long id) {
        this.id = id;
    }
}
