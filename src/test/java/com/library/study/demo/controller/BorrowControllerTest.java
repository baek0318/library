package com.library.study.demo.controller;

import com.library.study.demo.book.BookStatus;
import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.dto.LibraryDto;
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
    @Autowired
    private WebTestClient webTestClient;

    private TestInitializer testInitializer = new TestInitializer();

    private Long libraryId;
    private Long bookId;
    private Long userId;

    @BeforeEach
    void 테스트초기화() {
        testInitializer.setWebTestClient(webTestClient);

        LibraryDto.Request libraryReqDto = testInitializer.getLibraryReqDto();
        LibraryDto.Response libraryResDto = testInitializer.getLibraryResDto(libraryReqDto);
        libraryId = libraryResDto.getId();

        BookDto.Request bookReqDto = testInitializer.getBookReqDto();
        BookDto.Response bookResDto = testInitializer.getBookResDto(libraryId, bookReqDto);
        bookId = bookResDto.getId();

        SignUpReqDto userReqDto = testInitializer.getSignUpReqDto();
        SignUpResDto userResDto = testInitializer.getSignupResDto(userReqDto);
        userId = userResDto.getId();
    }

    @Test
    void 도서대여테스트() {
        //유저가 도서관에서 책을 빌린다...
        //도서관id, 유저id, 책id
        BorrowDto.Request reqDto = testInitializer.getBorrowReqDto(libraryId, bookId);
        BorrowDto.Response ResDto = testInitializer.getBorrowResDto(userId, reqDto);
        assertThat(ResDto.getBook().getId()).isEqualTo(bookId);
        assertThat(ResDto.getBook().getStatus()).isEqualTo(BookStatus.NOT_AVAILABLE);
        assertThat(ResDto.getUser().getId()).isEqualTo(userId);
    }

    @Test
    void 도서반납테스트() {
        BorrowDto.Request reqDto = testInitializer.getBorrowReqDto(libraryId, bookId);
        BorrowDto.Response ResDto = testInitializer.getBorrowResDto(userId, reqDto);

        webTestClient.post().uri("/api/users/" + userId + "/return")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), BorrowDto.Request.class)
                .exchange()
                .expectStatus().isOk();
    }
}
