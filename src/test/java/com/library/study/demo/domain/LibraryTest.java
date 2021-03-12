package com.library.study.demo.domain;

import com.library.study.demo.library.Library;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    @Test
    void create() {
        Library library = Library.builder()
                .name("library")
                .address("seoul")
                .build();
        assertThat(library.getName()).isEqualTo("library");
        assertThat(library.getAddress()).isEqualTo("seoul");
    }
}
