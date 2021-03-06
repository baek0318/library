package com.library.study.demo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    @Test
    void create() {
        Library library = Library.builder()
                .build();
    }
}
