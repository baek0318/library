package com.library.study.demo.controller;

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
public class LibraryControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    private Long libraryId;
    private LibraryDto.Request reqDto;
    private LibraryDto.Response resDto;

    @BeforeEach
    void 도서관생성초기화() {
        reqDto = LibraryDto.Request.builder()
                .address("testaddress")
                .name("testLibName")
                .build();

        resDto = webTestClient.post().uri("/api/librarys")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), LibraryDto.Request.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
        libraryId = resDto.getId();
    }

    @Test
    void 도서관생성테스트() {
        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getAddress()).isEqualTo(reqDto.getAddress());
    }

    @Test
    void 도서관수정테스트() {
        LibraryDto.Request reqDto = LibraryDto.Request.builder()
                .name("정신도서관")
                .build();
        resDto = webTestClient.patch().uri("/api/librarys/" + libraryId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), LibraryDto.Request.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
    }

    @Test
    void 도서관삭제테스트() {

    }

    @Test
    void 도서관조회테스트() {

    }
}
