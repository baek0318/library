package com.library.study.demo.domain;

import org.junit.jupiter.api.Test;

public class LibraryTest {

    @Test
    void create() {
        Library library = Library.builder()
                .build();
    }
}
