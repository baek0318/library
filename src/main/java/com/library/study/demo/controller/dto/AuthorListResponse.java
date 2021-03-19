package com.library.study.demo.controller.dto;

import com.library.study.demo.domain.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AuthorListResponse {

    private List<Author> authorList;

    public AuthorListResponse(List<Author> authorList) {
        this.authorList = authorList;
    }
}
