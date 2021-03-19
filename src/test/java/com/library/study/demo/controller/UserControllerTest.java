package com.library.study.demo.controller;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.dto.LibraryDto;
import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    private TestInitializer testInitializer = new TestInitializer();

    private Long libraryId;
    private Long bookId1;
    private Long bookId2;
    private Long userId;

    void 대여조회초기화() {
        testInitializer.setWebTestClient(webTestClient);
        LibraryDto.Request libraryReqDto = testInitializer.getLibraryReqDto();
        LibraryDto.Response libraryResDto = testInitializer.getLibraryResDto(libraryReqDto);
        libraryId = libraryResDto.getId();

        BookDto.Request bookReqDto1 = testInitializer.getBookReqDto();
        BookDto.Response bookResDto1 = testInitializer.getBookResDto(libraryId, bookReqDto1);
        bookId1 = bookResDto1.getId();

        BookDto.Request bookReqDto2 = testInitializer.getBookReqDto();
        BookDto.Response bookResDto2 = testInitializer.getBookResDto(libraryId, bookReqDto2);
        bookId2 = bookResDto2.getId();

        SignUpReqDto userReqDto = testInitializer.getSignUpReqDto();
        SignUpResDto userResDto = testInitializer.getSignupResDto(userReqDto);
        userId = userResDto.getId();

        BorrowDto.Request borrowReqDto1 = testInitializer.getBorrowReqDto(libraryId, bookId1);
        testInitializer.getBorrowResDto(userId, borrowReqDto1);

        BorrowDto.Request borrowReqDto2 = testInitializer.getBorrowReqDto(libraryId, bookId2);
        testInitializer.getBorrowResDto(userId, borrowReqDto2);
    }

    @Test
    void 책_대여_목록() {
        대여조회초기화();
        List<BorrowDto.Response> borrowBookList = webTestClient.get().uri("/api/users/" + userId + "/borrowings")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(BorrowDto.Response.class)
                .returnResult()
                .getResponseBody();

        assertThat(borrowBookList).isNotNull();
        assertThat(borrowBookList.size()).isEqualTo(2);
    }
}
