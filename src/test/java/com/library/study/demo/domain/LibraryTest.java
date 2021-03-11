package com.library.study.demo.domain;

import com.library.study.demo.library.Library;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {

    @Test
    void create() {
        Library library = new Library("library");
        assertThat(library.getName()).isEqualTo("library");
    }
}
