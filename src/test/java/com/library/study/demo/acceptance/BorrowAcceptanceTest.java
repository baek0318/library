package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.borrow.BorrowRequest;
import com.library.study.demo.controller.dto.borrow.BorrowResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BorrowAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("책빌린 정보를 저장")
    void saveBorrow() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        BorrowRequest borrowDto = new BorrowRequest( 1L);
        HttpEntity<BorrowRequest> request = new HttpEntity<>(borrowDto, headers);

        ResponseEntity<BorrowResponse> responseEntity = restTemplate
                .postForEntity(
                        "/borrow/{user-id}",
                        request,
                        BorrowResponse.class,
                        1L
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(2L);
    }
}
