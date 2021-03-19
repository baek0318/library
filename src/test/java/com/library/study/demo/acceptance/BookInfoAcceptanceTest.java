package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static com.library.study.demo.acceptance.AuthorAcceptanceTest.작가_생성됨;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookInfoAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private SaveAuthorResponse saveAuthorResponse;
    private SaveBookInfoResponse saveBookInfoResponse1;
    private SaveBookInfoResponse saveBookInfoResponse2;


    @BeforeEach
    void setUp() {
        this.saveAuthorResponse = 작가_생성됨(restTemplate, "jon");
        this.saveBookInfoResponse1 = 책_정보_생성됨(restTemplate, "math", saveAuthorResponse.getId());
        this.saveBookInfoResponse2 = 책_정보_생성됨(restTemplate, "math2", saveAuthorResponse.getId());
    }

    @Test
    void 책_정보_생성_테스트() {
        HttpHeaders headers = new HttpHeaders();

        SaveBookInfoRequest bookInfoRequest = new SaveBookInfoRequest("수학의 정석", saveAuthorResponse.getId());

        HttpEntity<SaveBookInfoRequest> request = new HttpEntity(bookInfoRequest, headers);

        ResponseEntity<SaveBookInfoResponse> responseEntity = restTemplate
                .postForEntity("/bookinfo/save", request, SaveBookInfoResponse.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void 책_정보_목록_테스트() {
        ResponseEntity<BookInfoListResponse> responseEntity = restTemplate.getForEntity("/bookinfo/list", BookInfoListResponse.class);

        Map<Long, String> map = new HashMap<>();

        responseEntity.getBody().getBookInfoResponseList()
                .forEach(it -> map.put(it.getId(), it.getTitle()));

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("math", map.get(saveBookInfoResponse1.getId()));
        Assertions.assertEquals("math2", map.get(saveBookInfoResponse2.getId()));
    }

    public static SaveBookInfoResponse 책_정보_생성됨(TestRestTemplate template, String bookInfoTitle, Long authorId) {
        HttpHeaders headers = new HttpHeaders();

        SaveBookInfoRequest bookInfoRequest = new SaveBookInfoRequest(bookInfoTitle, authorId);

        HttpEntity<SaveBookInfoRequest> request = new HttpEntity(bookInfoRequest, headers);

        ResponseEntity<SaveBookInfoResponse> response = template
                .postForEntity("/bookinfo/save", request, SaveBookInfoResponse.class);

        return response.getBody();
    }
}
