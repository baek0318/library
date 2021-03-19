package com.library.study.demo.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveBookInfoRequest {

    private String title;

    private Long authorId;

    public SaveBookInfoRequest(String title, Long authorId) {
        this.title = title;
        this.authorId = authorId;
    }
}
