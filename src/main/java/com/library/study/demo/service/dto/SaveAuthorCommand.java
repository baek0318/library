package com.library.study.demo.service.dto;

import lombok.Getter;

@Getter
public class SaveAuthorCommand {

    private String name;

    public SaveAuthorCommand(String name) {
        this.name = name;
    }

}
