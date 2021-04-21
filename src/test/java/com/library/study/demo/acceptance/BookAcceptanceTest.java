package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.book.BookResponse;
import com.library.study.demo.controller.dto.book.SaveBookRequest;
import com.library.study.demo.controller.dto.book.SaveBookResponse;
import com.library.study.demo.controller.dto.book.UpdateBookRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("책 저장하기")
    void bookSaveTest() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        SaveBookRequest saveDto = new SaveBookRequest(1L);

        HttpEntity<SaveBookRequest> request = new HttpEntity<>(saveDto, headers);

        ResponseEntity<SaveBookResponse> responseEntity = restTemplate
                .postForEntity(
                        "/book",
                        request,
                        SaveBookResponse.class
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("책 정보 가져오기")
    void getBookTest() {

        ResponseEntity<BookResponse> responseEntity = restTemplate
                .getForEntity(
                        "/book/{book-id}",
                        BookResponse.class,
                        1L
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("책 정보 업데이트하기")
    void updateBookTest() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        UpdateBookRequest updateDto = new UpdateBookRequest(false);
        HttpEntity<UpdateBookRequest> reqeust = new HttpEntity<>(updateDto,headers);

        ResponseEntity<BookResponse> responseEntity = restTemplate
                .exchange(
                        "/book/{book-id}",
                        HttpMethod.PUT,
                        reqeust,
                        BookResponse.class,
                        1L
                );

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getStatus()).isEqualTo(false);
    }
}
