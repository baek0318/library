package com.library.study.demo.controller.dto;

import com.library.study.demo.domain.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AuthorListResponse {

    private List<AuthorResponse> authorList;

    public AuthorListResponse(List<AuthorResponse> authorList) {
        this.authorList = authorList;
    }
}
