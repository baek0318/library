package com.library.study.demo.exception;

import lombok.Getter;

// TODO :: 에러코드 삽입
@Getter
public enum ErrorCode {

    //Common

    //Join
    ID_DUPLICATION(400,"J001","Id is Duplicated")
    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message){
        this.status=status;
        this.message=message;
        this.code=code;
    }
}
