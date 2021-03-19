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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    private TestInitializer testInitializer = new TestInitializer();

    private Long libraryId;
    private LibraryDto.Request reqDto;
    private LibraryDto.Response resDto;

    @BeforeEach
    void 도서관생성초기화() {
        testInitializer.setWebTestClient(webTestClient);

        LibraryDto.Request libraryReqDto = testInitializer.getLibraryReqDto();
        libraryId = createLibraryNameOf(TestInitializer.LIBRARY_TEST_NAME);
    }

    @Test
    void 도서관생성테스트() {
        LibraryDto.Request reqDto = testInitializer.getLibraryReqDto();
        LibraryDto.Response resDto = testInitializer.getLibraryResDto(reqDto);
        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
        assertThat(resDto.getAddress()).isEqualTo(reqDto.getAddress());
    }

    @Test
    void 도서관수정테스트() {
        LibraryDto.Request reqDto = LibraryDto.Request.builder()
                .name("정신도서관")
                .build();
        LibraryDto.Response resDto = webTestClient.patch().uri("/api/librarys/" + libraryId.toString())
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
    void 도서관모두조회테스트() {
        List<String> libraryNameList = Arrays.asList("서울", "대구", "대전", "부산", "제주");
        List<Long> libraryIdList = libraryNameList.stream()
                .map(this::createLibraryNameOf)
                .collect(Collectors.toList());

        List<LibraryDto.Response> resDtoList = webTestClient.get().uri("/api/librarys")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
        assertThat(resDtoList).isNotNull();
        List<String> findNameList = resDtoList.stream()
                .map(LibraryDto.Response::getName)
                .collect(Collectors.toList());
        assertThat(findNameList).containsAll(libraryNameList);
    }

    @Test
    void 도서관삭제테스트() {
        webTestClient.delete().uri("/api/librarys/" + libraryId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult();
    }

    @Test
    void 도서관하나조회테스트() {
        LibraryDto.Response resDto = webTestClient.get().uri("/api/librarys/" + libraryId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();

        assertThat(resDto).isNotNull();
        assertThat(resDto.getName()).isEqualTo(TestInitializer.LIBRARY_TEST_NAME);
    }

    Long createLibraryNameOf(String name) {
        LibraryDto.Request reqDto = LibraryDto.Request.builder()
                .address(TestInitializer.LIBRARY_TEST_ADDRESS)
                .name(name)
                .build();
        LibraryDto.Response resDto = webTestClient.post().uri("/api/librarys")
                .body(Mono.just(reqDto), LibraryDto.Request.class)
                .exchange()
                .expectBody(LibraryDto.Response.class)
                .returnResult()
                .getResponseBody();
        return resDto.getId();
    }
}
