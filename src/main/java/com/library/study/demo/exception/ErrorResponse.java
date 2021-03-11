package com.library.study.demo.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String message;
    private int status;
    private List<ErrorField> errors;
    private String code;

    private ErrorResponse(final ErrorCode code, final List<ErrorField> errors){
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code){
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
        this.code = code.getCode();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ErrorField{
        private String field;
        private String value;
        private String reason;
    }
    // TODO :: of 메소드 구현
//    public ErrorResponse of()
}
