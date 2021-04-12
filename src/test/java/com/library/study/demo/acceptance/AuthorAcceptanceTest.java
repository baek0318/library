package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.AuthorResponse;
import com.library.study.demo.controller.dto.SaveAuthorRequest;
import com.library.study.demo.controller.dto.SaveAuthorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorAcceptanceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void 작가_생성_테스트() {
        SaveAuthorRequest authorRequest = new SaveAuthorRequest("jon");

        HttpEntity<SaveAuthorRequest> request = new HttpEntity(authorRequest, new HttpHeaders());

        ResponseEntity<SaveAuthorResponse> response = restTemplate.postForEntity("/author/save", request, SaveAuthorResponse.class);

        Assertions.assertEquals(response.getBody().getId(), 1L);
    }

    @Test
    void 작가_조회_테스트() {
        SaveAuthorResponse saveAuthorResponse = 작가_생성됨(restTemplate, "jon");
        ResponseEntity<AuthorResponse> response = restTemplate.getForEntity("/author?id="+saveAuthorResponse.getId(), AuthorResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(response.getBody().getId(), saveAuthorResponse.getId());
        Assertions.assertEquals(response.getBody().getName(), "jon");
    }

    public static SaveAuthorResponse 작가_생성됨(TestRestTemplate template, String authorName) {
        SaveAuthorRequest authorRequest = new SaveAuthorRequest(authorName);

        HttpEntity<SaveAuthorRequest> request = new HttpEntity(authorRequest, new HttpHeaders());

        ResponseEntity<SaveAuthorResponse> response = template.postForEntity("/author/save", request, SaveAuthorResponse.class);

        return response.getBody();
    }

}
