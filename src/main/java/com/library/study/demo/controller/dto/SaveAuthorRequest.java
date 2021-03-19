package com.library.study.demo.controller.dto;

import com.library.study.demo.service.dto.SaveAuthorCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveAuthorRequest {

    private String name;

    public SaveAuthorRequest(String name) {
        this.name = name;
    }

    public SaveAuthorCommand toCommand() {
        return new SaveAuthorCommand(name);
    }
}
