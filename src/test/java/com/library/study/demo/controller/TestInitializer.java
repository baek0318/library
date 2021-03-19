package com.library.study.demo.controller;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.dto.LibraryDto;
import com.library.study.demo.user.Role;
import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestInitializer {
    public static final String LIBRARY_TEST_ADDRESS = "testLibAddress";
    public static final String LIBRARY_TEST_NAME = "testLibName";
    public static final String BOOK_TEST_TITLE = "testBookTitle";
    public static final String BOOK_TEST_AUTHOR = "testBookAuthor";
    public static final String BOOK_TEST_ISBN = "testBookISBN";
    public static final String USER_TEST_ID = "testid";
    public static final String USER_TEST_PASSWORD = "testpw1235~@@";
    public static final String USER_TEST_NAME = "testername";

    private WebTestClient webTestClient;

    public void setWebTestClient(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    public SignUpReqDto getSignUpReqDto() {
        return SignUpReqDto.builder()
                .loginId(USER_TEST_ID)
                .password(USER_TEST_PASSWORD)
                .name(USER_TEST_NAME)
                .role(Role.USER)
                .build();
    }

    public SignUpResDto getSignupResDto(SignUpReqDto userReqDto) {
        return webTestClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userReqDto), SignUpReqDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(SignUpResDto.class)
                .returnResult()
                .getResponseBody();
    }

    public BookDto.Request getBookReqDto() {
        return BookDto.Request.builder()
                .title(BOOK_TEST_TITLE)
                .author(BOOK_TEST_AUTHOR)
                .isbn(BOOK_TEST_ISBN)
                .build();
    }

    public BookDto.Response getBookResDto(Long libraryId, BookDto.Request bookReqDto) {
        return webTestClient.post().uri("/api/librarys/{id}/books", libraryId)
                .body(Mono.just(bookReqDto), BookDto.Request.class)
                .exchange()
                .expectBody(BookDto.Response.class)
                .returnResult()
                .getResponseBody();
    }

    public LibraryDto.Request getLibraryReqDto() {
        return LibraryDto.Request.builder()
                .address(LIBRARY_TEST_ADDRESS)
                .name(LIBRARY_TEST_NAME)
                .build();
    }

    public LibraryDto.Response getLibraryResDto(LibraryDto.Request libraryReqDto) {
        return webTestClient.post().uri("/api/librarys")
                .body(Mono.just(libraryReqDto), LibraryDto.Request.class)
                .exchange()
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
    }

    public BorrowDto.Request getBorrowReqDto(Long libraryId, Long bookId) {
        return BorrowDto.Request.builder()
                .libraryId(libraryId)
                .bookId(bookId)
                .build();
    }

    public BorrowDto.Response getBorrowResDto(Long userId, BorrowDto.Request reqDto) {
        return webTestClient.post().uri("/api/users/" + userId + "/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), BorrowDto.Request.class)
                .exchange()
                .expectBody(BorrowDto.Response.class)
                .returnResult()
                .getResponseBody();
    }
}
