package com.library.study.demo.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveUserResponse {

    private Long id;

    public SaveUserResponse(Long id) {
        this.id = id;
    }
}
