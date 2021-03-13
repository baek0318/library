package com.library.study.demo.domain;

import com.library.study.demo.book.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTest {

    @Test
    void create() {
        Book book = Book.builder()
                .title("Tobi")
                .author("tobi")
                .isbn("101010")
                .build();

        assertThat(book.getTitle()).isEqualTo("Tobi");
        assertThat(book.getAuthor()).isEqualTo("tobi");
        assertThat(book.getIsbn()).isEqualTo("101010");
    }
}
