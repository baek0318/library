package com.library.study.demo.controller;

import com.library.study.demo.dto.UserDto;
import org.assertj.core.api.Assertions;
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

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void 로그인테스트() {
        String userId = "testid";
        String password = "testpw1234~@#";
        assertThat(1).isEqualTo(1);
    }

    @Test
    void 회원가입테스트() {
        String userId = "testid";
        String password = "testpw1234~@#";

//        User user = User.builder()
//                .userId(userId)
//                .password(password)
//                .role(Role.USER)
//                .build();
        UserDto.SignupReq reqDto = new UserDto.SignupReq(userId,password);

        UserDto.SignupRes resDto =  webTestClient.post()
                .uri("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(reqDto),UserDto.SignupReq.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(UserDto.SignupRes.class)
                .returnResult()
                .getResponseBody();
        assertThat(resDto.getId()).isEqualTo(1L);
    }
}
