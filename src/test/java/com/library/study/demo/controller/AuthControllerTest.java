package com.library.study.demo.controller;

import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    private TestInitializer testInitializer = new TestInitializer();

    @BeforeEach
    void setUp() {
        testInitializer.setWebTestClient(webTestClient);
    }

    @Test
    void 회원가입_성공() {
        SignUpReqDto reqDto = testInitializer.getSignUpReqDto();
        SignUpResDto resDto = testInitializer.getSignupResDto(reqDto);
        assertThat(resDto.getName()).isEqualTo(reqDto.getName());
    }


    //TODO::3-19 중복처리
//    @Test
//    void 회원가입_중복() {
//
//    }
}
