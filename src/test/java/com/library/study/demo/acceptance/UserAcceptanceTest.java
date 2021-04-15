package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.SaveUserRequest;
import com.library.study.demo.controller.dto.SaveUserResponse;
import org.assertj.core.api.Assertions;
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

    @Test
    @DisplayName("유저 저장하기")
    void testUserSave() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        SaveUserRequest userDto = new SaveUserRequest("peach@kakao.com", "1234");
        HttpEntity<SaveUserRequest> request = new HttpEntity<SaveUserRequest>(userDto, headers);

        ResponseEntity<SaveUserResponse> responseEntity = restTemplate
                .postForEntity("/user",
                        request,
                        SaveUserResponse.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
