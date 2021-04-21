package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.user.SaveUserRequest;
import com.library.study.demo.controller.dto.user.SaveUserResponse;
import com.library.study.demo.controller.dto.user.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("유저 저장하기")
    void testUserSave() {

        SaveUserRequest userDto = new SaveUserRequest("peach@kakao.com", "1234");
        HttpEntity<SaveUserRequest> request = new HttpEntity<SaveUserRequest>(userDto, headers);

        ResponseEntity<SaveUserResponse> responseEntity = restTemplate
                .postForEntity("/user",
                        request,
                        SaveUserResponse.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("유저 정보 가져오기")
    void testGetUserInfo() {

        ResponseEntity<UserResponse> responseEntity = restTemplate
                .getForEntity(
                        "/user/{user-id}",
                        UserResponse.class,
                        1L);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getEmail()).isEqualTo("peach18@kakao.com");
    }
}
