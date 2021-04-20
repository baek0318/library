package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void 작가_생성_테스트() {
        SaveAuthorRequest authorRequest = new SaveAuthorRequest("jon");

        HttpEntity<SaveAuthorRequest> request = new HttpEntity(authorRequest, new HttpHeaders());

        ResponseEntity<SaveAuthorResponse> response = restTemplate
                .postForEntity(
                        "/author",
                        request,
                        SaveAuthorResponse.class
                );

        Assertions.assertEquals(response.getBody().getId(), 2L);
    }

    @Test
    @DisplayName("작가 이름으로 찾기")
    void testAuthorFindByName() {

        ResponseEntity<AuthorListResponse> responseEntity = restTemplate
                .getForEntity(
                        "/author?name=baek",
                        AuthorListResponse.class
                );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody().getAuthorList());
        List<AuthorResponse> response = responseEntity.getBody().getAuthorList();
        for(int i = 1; i <= response.size(); i++){
            System.out.println("author id:"+response.get(i-1).getId()+" author name:"+response.get(i-1).getName());
            Assertions.assertEquals(i, response.get(i-1).getId());
        }
    }

    @Test
    @DisplayName("아이디로 작가 조회하기")
    void testAuthorFindById() {

        ResponseEntity<AuthorResponse> responseEntity = restTemplate
                .getForEntity(
                        "/author/{author-id}",
                        AuthorResponse.class,
                        1L
                );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("baek", responseEntity.getBody().getName());
        Assertions.assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    @DisplayName("작가 이름 업데이트 하기")
    void testAuthorNameUpdate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        UpdateAuthorRequest updateDto = new UpdateAuthorRequest(2L, "baeks");

        HttpEntity<UpdateAuthorRequest> requestEntity = new HttpEntity(updateDto ,headers);

        ResponseEntity<AuthorResponse> responseEntity = restTemplate
                .exchange(
                        "/author",
                        HttpMethod.PUT,
                        requestEntity,
                        AuthorResponse.class
                );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(2L, responseEntity.getBody().getId());
        Assertions.assertEquals("baeks", responseEntity.getBody().getName());

    }

    public static SaveAuthorResponse 작가_생성됨(TestRestTemplate template, String authorName) {
        SaveAuthorRequest authorRequest = new SaveAuthorRequest(authorName);

        HttpEntity<SaveAuthorRequest> request = new HttpEntity(authorRequest, new HttpHeaders());

        ResponseEntity<SaveAuthorResponse> response = template.postForEntity("/author/save", request, SaveAuthorResponse.class);

        return response.getBody();
    }

}
