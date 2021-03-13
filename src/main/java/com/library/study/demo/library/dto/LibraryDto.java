package com.library.study.demo.library.dto;

import com.library.study.demo.library.Library;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LibraryDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String name;
        private String address;

        @Builder
        Request(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public Library toEntity() {
            return Library.builder()
                    .name(name)
                    .address(address)
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private String name;
        private String address;

        @Builder
        Response(Long id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }
    }
}
