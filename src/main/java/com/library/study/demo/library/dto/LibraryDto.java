package com.library.study.demo.library.dto;

import com.library.study.demo.library.Library;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private String name;

        public Library toEntity(){
            return new Library(name);
        }
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResponse{
        private String name;
    }
}
