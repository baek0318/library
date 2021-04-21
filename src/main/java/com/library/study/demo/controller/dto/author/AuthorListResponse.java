package com.library.study.demo.controller.dto.author;

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
