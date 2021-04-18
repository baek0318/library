package com.library.study.demo.controller.dto;

import com.library.study.demo.domain.Author;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorUpdateRequest {

    private Long id;

    private String name;

    public AuthorUpdateRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
