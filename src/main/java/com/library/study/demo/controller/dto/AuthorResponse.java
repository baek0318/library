package com.library.study.demo.controller.dto;

import com.library.study.demo.domain.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthorResponse {

    private Long id;

    private String name;

    public AuthorResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}
