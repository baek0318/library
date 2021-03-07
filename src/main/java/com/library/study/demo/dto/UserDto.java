package com.library.study.demo.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @Getter
    @Setter
    public static class SignupReq{
        private String userId;
        private String password;

        public SignupReq(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }
    }

    @Getter
    @Setter
    public static class SignupRes{
        private Long id;
        public SignupRes(Long id){
            this.id = id;
        }
    }
}
