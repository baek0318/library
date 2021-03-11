package com.library.study.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import javax.swing.*;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setup(){

    }

    @Test
    void 도서관삽입테스트(){
        webTestClient.post().uri("/api/librarys")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just())
    }
    @Test
    void 도서관수정테스트(){

    }
    @Test
    void 도서관삭제테스트(){

    }
    @Test
    void 도서관조회테스트(){

    }
}
