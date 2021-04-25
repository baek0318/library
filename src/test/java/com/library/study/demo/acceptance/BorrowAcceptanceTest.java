package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.borrow.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BorrowAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("책빌린 정보를 저장")
    void saveBorrow() {
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

    @Test
    @DisplayName("빌린 정보 가져오기")
    void getBorrowInfo() {

        ResponseEntity<BorrowInfoResponse> responseEntity = restTemplate
                .getForEntity(
                        "/borrow/{borrow-id}",
                        BorrowInfoResponse.class,
                        1L
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getBorrowDate()).isEqualTo(LocalDate.of(2021, 4, 23));
        Assertions.assertThat(responseEntity.getBody().getReturnDate()).isNull();
    }

    @Test
    @DisplayName("특정유저 빌린 정보 리스트 불러오기")
    void getBorrowInfoList() {

        ResponseEntity<BorrowInfoListResponse> responseEntity = restTemplate
                .getForEntity(
                        "/borrow/{user-id}/list",
                        BorrowInfoListResponse.class,
                        1L
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        responseEntity.getBody().getBorrowInfoResponses()
                .forEach(it -> {
                    Assertions.assertThat(it.getBorrowDate()).isNotNull();
                });
    }

    @Test
    @DisplayName("반납하기")
    void returnBook() {
        UpdateBorrowRequest updateDto = new UpdateBorrowRequest("2021-04-25");
        HttpEntity<UpdateBorrowRequest> request = new HttpEntity(updateDto, headers);

        ResponseEntity<BorrowResponse> responseEntity = restTemplate
                .exchange(
                        "/borrow/{borrow-id}",
                        HttpMethod.PUT,
                        request,
                        BorrowResponse.class,
                        1L
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
    }
}
