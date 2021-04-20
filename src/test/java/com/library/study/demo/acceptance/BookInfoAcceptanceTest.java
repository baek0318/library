package com.library.study.demo.acceptance;

import com.library.study.demo.controller.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

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
        ResponseEntity<BookInfoListResponse> responseEntity = restTemplate
                .getForEntity("/bookinfo/list", BookInfoListResponse.class);

        Map<Long, String> map = new HashMap<>();

        responseEntity.getBody().getBookInfoResponseList()
                .forEach(it -> map.put(it.getId(), it.getTitle()));

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("math", map.get(saveBookInfoResponse1.getId()));
        Assertions.assertEquals("math2", map.get(saveBookInfoResponse2.getId()));
    }

    @Test
    @DisplayName("책 정보 가져오기 테스트")
    void getBookInfoTest() {
        ResponseEntity<BookInfoResponse> responseEntity = restTemplate
                .getForEntity("/bookinfo?id=1",BookInfoResponse.class);

        BookInfoResponse response = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(1L, response.getId());
    }

    @Test
    @DisplayName("책 이름으로 가져오기")
    void getBookInfoByNameTest() {
        ResponseEntity<BookInfoListResponse> responseEntity = restTemplate
                .getForEntity(
                        "/bookinfo?title=peachberr",
                        BookInfoListResponse.class
                );

        BookInfoListResponse response = responseEntity.getBody();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(response.getBookInfoResponseList());
        int count = 0;
        for (BookInfoResponse bookInfo : response.getBookInfoResponseList()) {
            if (bookInfo.getTitle().contains("peachberr")) {
                count++;
            }
        }
        Assertions.assertEquals(response.getBookInfoResponseList().size(), count);
    }

    @Test
    @DisplayName("책 정보 수정하기 테스트")
    void updateBookInfoNameTest() {
        HttpHeaders headers = new HttpHeaders();
        UpdateBookInfoRequest requestDto = new UpdateBookInfoRequest("peachberry");

        HttpEntity<UpdateBookInfoRequest> request = new HttpEntity(requestDto, headers);
        ResponseEntity<BookInfoResponse> responseEntity = restTemplate
                .exchange(
                        "/bookinfo/{bookinfo-id}",
                        HttpMethod.PUT,
                        request,
                        BookInfoResponse.class,
                        1L
                );

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("peachberry", responseEntity.getBody().getTitle());
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
