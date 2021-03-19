package com.library.study.demo.controller;

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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private Long bookId;
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

        BookDto.Request bookReqDto = BookDto.Request.builder()
                .title(BOOK_TEST_TITLE)
                .author(BOOK_TEST_AUTHOR)
                .isbn(BOOK_TEST_ISBN)
                .build();
        BookDto.Response bookResDto = webTestClient.post().uri("/api/librarys/{id}/books", libraryId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(bookReqDto), BookDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BookDto.Response.class)
                .returnResult()
                .getResponseBody();
        bookId = bookResDto.getId();
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
        webTestClient.delete().uri("/api/librarys/" + libraryId + "/books/" + bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult();
    }

    @Test
    void 책하나조회() {
        BookDto.Response resDto = webTestClient.get().uri("/api/librarys/" + libraryId + "/books/" + bookId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BookDto.Response.class)
                .returnResult()
                .getResponseBody();

        assertThat(resDto).isNotNull();
        assertThat(resDto.getTitle()).isEqualTo(BOOK_TEST_TITLE);
        assertThat(resDto.getAuthor()).isEqualTo(BOOK_TEST_AUTHOR);
        assertThat(resDto.getIsbn()).isEqualTo(BOOK_TEST_ISBN);
    }

    @Test
    void 책모두조회() {
        List<String> bookNameList = Arrays.asList("탈태", "토지", "귀멸의칼날", "킹덤", "무한도전");
        List<Long> bookIdList = bookNameList.stream()
                .map(this::createBookNameOf)
                .collect(Collectors.toList());

        List<BookDto.Response> resDtoList = webTestClient.get().uri("/api/librarys/" + libraryId + "/books/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(BookDto.Response.class)
                .returnResult()
                .getResponseBody();

        assertThat(resDtoList).isNotNull();
        List<String> findNameList = resDtoList.stream()
                .map(BookDto.Response::getTitle)
                .collect(Collectors.toList());
        assertThat(findNameList).containsAll(bookNameList);
    }

    @Test
    void 책정보수정() {

    }

    Long createBookNameOf(String name) {
        LibraryDto.Request reqDto = LibraryDto.Request.builder()
                .address(LIBRARY_TEST_ADDRESS)
                .name(name)
                .build();
        BookDto.Request bookReqDto = BookDto.Request.builder()
                .title(name)
                .author(BOOK_TEST_AUTHOR)
                .isbn(BOOK_TEST_ISBN)
                .build();

        BookDto.Response bookResDto = webTestClient.post().uri("/api/librarys/{id}/books", libraryId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(bookReqDto), BookDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BookDto.Response.class)
                .returnResult()
                .getResponseBody();
        return bookResDto.getId();
    }
}
