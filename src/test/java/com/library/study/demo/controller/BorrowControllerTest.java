package com.library.study.demo.controller;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.dto.LibraryDto;
import com.library.study.demo.user.Role;
import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
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
public class BorrowControllerTest {
    private static final String LIBRARY_TEST_ADDRESS = "testLibAddress";
    private static final String LIBRARY_TEST_NAME = "testLibName";
    private static final String BOOK_TEST_TITLE = "testBookTitle";
    private static final String BOOK_TEST_AUTHOR = "testBookAuthor";
    private static final String BOOK_TEST_ISBN = "testBookISBN";
    private static final String USER_TEST_ID = "testid";
    private static final String USER_TEST_PASSWORD = "testpw1235~@@";

    @Autowired
    private WebTestClient webTestClient;

    private Long libraryId;
    private Long bookId;
    private Long userId;

    @BeforeEach
    void 테스트초기화() {
        LibraryDto.Request libraryReqDto = LibraryDto.Request.builder()
                .address(LIBRARY_TEST_ADDRESS)
                .name(LIBRARY_TEST_NAME)
                .build();
        LibraryDto.Response libraryResDto = webTestClient.post().uri("/api/librarys")
                .body(Mono.just(libraryReqDto), LibraryDto.Request.class)
                .exchange()
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
        libraryId = libraryResDto.getId();

        BookDto.Request bookReqDto = BookDto.Request.builder()
                .title(BOOK_TEST_TITLE)
                .author(BOOK_TEST_AUTHOR)
                .isbn(BOOK_TEST_ISBN)
                .build();
        BookDto.Response bookResDto = webTestClient.post().uri("/api/librarys/{id}/books", libraryId)
                .body(Mono.just(bookReqDto), BookDto.Request.class)
                .exchange()
                .expectBody(BookDto.Response.class)
                .returnResult()
                .getResponseBody();
        bookId = bookResDto.getId();

        SignUpReqDto userReqDto = SignUpReqDto.builder()
                .loginid(USER_TEST_ID)
                .password(USER_TEST_PASSWORD)
                .role(Role.USER)
                .build();

        SignUpResDto userResDto = webTestClient.post()
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
        userId = userResDto.getId();
    }

    @Test
    void 도서대여테스트() {
        //유저가 도서관에서 책을 빌린다...
        //도서관id, 유저id, 책id
        BorrowDto.Request reqDto = BorrowDto.Request.builder()
                .libraryId(libraryId)
                .bookId(bookId)
                .build();

        BorrowDto.Response ResDto = webTestClient.post().uri("/api/users/" + userId + "/borrow")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), BorrowDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(BorrowDto.Response.class)
                .returnResult()
                .getResponseBody();
        assertThat(ResDto.getBook().getId()).isEqualTo(bookId);
        assertThat(ResDto.getUser().getId()).isEqualTo(userId);
    }
}
