package com.library.study.demo.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateBookRequest {

    private Boolean status;

    public UpdateBookRequest(Boolean status) {
        this.status = status;
    }
}
