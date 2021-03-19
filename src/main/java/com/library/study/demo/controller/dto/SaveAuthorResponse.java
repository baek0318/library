package com.library.study.demo.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveAuthorResponse {

    private Long id;

    public SaveAuthorResponse(Long id) {
        this.id = id;
    }
}
