package com.library.study.demo.controller;

import com.library.study.demo.user.Role;
import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthControllerTest {
    private static final String USER_TEST_ID = "testid";
    private static final String USER_TEST_PASSWORD = "testpw1235~@@";
    private static final String USER_TEST_NAME = "testname";
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void 회원가입_성공() {
        SignUpReqDto reqDto = SignUpReqDto.builder()
                .loginId(USER_TEST_ID)
                .password(USER_TEST_PASSWORD)
                .name(USER_TEST_NAME)
                .role(Role.USER)
                .build();

        SignUpResDto resDto = webTestClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto), SignUpReqDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(SignUpResDto.class)
                .returnResult()
                .getResponseBody();
        assertThat(resDto.getId()).isEqualTo(1L);
    }
    //TODO::3-19 중복처리
//    @Test
//    void 회원가입_중복() {
//
//    }
}
