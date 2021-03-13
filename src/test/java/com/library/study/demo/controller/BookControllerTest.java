package com.library.study.demo.controller;

import com.library.study.demo.book.Book;
import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.dto.LibraryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
    private static final String LIBRARY_TEST_ADDRESS = "testLibAddress";
    private static final String LIBRARY_TEST_NAME = "testLibName";
    private static final String BOOK_TEST_TITLE = "testBookTitle";
    private static final String BOOK_TEST_AUTHOR = "testBookAuthor";
    private static final String BOOK_TEST_ISBN = "testBookISBN";

    @Autowired
    private WebTestClient webTestClient;
    private Long libraryId;

    @BeforeEach
    public void 도서관초기화() {
        LibraryDto.Request reqDto = LibraryDto.Request.builder()
                .address(LIBRARY_TEST_ADDRESS)
                .name(LIBRARY_TEST_NAME)
                .build();
        LibraryDto.Response resDto = webTestClient.post().uri("/api/librarys")
                .body(Mono.just(reqDto), LibraryDto.Request.class)
                .exchange()
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
        libraryId = resDto.getId();
    }

    @Test
    void 책등록() {
        BookDto.Request reqDto = BookDto.Request.builder()
                .title(BOOK_TEST_TITLE)
                .author(BOOK_TEST_AUTHOR)
                .isbn(BOOK_TEST_ISBN)
                .build();
        BookDto.Response resDto = webTestClient.post().uri("/api/librarys/{id}/books", libraryId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), BookDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BookDto.Response.class)
                .returnResult()
                .getResponseBody();
        assertThat(resDto.getTitle()).isEqualTo(reqDto.getTitle());
        assertThat(resDto.getAuthor()).isEqualTo(reqDto.getAuthor());
        assertThat(resDto.getIsbn()).isEqualTo(reqDto.getIsbn());
    }

    @Test
    void 책삭제() {
//        webTestClient.delete().uri("/api/librarys/" + libraryId + "/books/" + bookId)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody()
//                .returnResult();
    }

    @Test
    void 책하나조회() {

    }

    @Test
    void 책모두조회() {

    }

}
