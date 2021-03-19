package com.library.study.demo.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveBookInfoResponse {

    private Long id;

    public SaveBookInfoResponse(Long id) {
        this.id = id;
    }

}
