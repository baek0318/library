package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.bookinfo.*;
import com.library.study.demo.domain.BookInfo;
import com.library.study.demo.service.BookInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookinfo")
public class BookInfoController {

    private final BookInfoService bookInfoService;

    public BookInfoController(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @PostMapping("/save")
    public ResponseEntity<SaveBookInfoResponse> save(@RequestBody SaveBookInfoRequest saveBookInfoRequest) {
        Long id = bookInfoService.save(saveBookInfoRequest.getTitle(), saveBookInfoRequest.getAuthorId());

        return ResponseEntity.ok(new SaveBookInfoResponse(id));
    }

    @GetMapping("/list")
    public ResponseEntity<BookInfoListResponse> getBookInfoList() {
        return ResponseEntity.ok(bookInfoService.findAll());
    }

    @GetMapping("")
    public ResponseEntity<?> getBookInfo(@RequestParam Map<String, String> param) {

        ResponseEntity<?> responseEntity = ResponseEntity.ok().build();
        responseEntity = getResponseEntity(param, responseEntity);

        return responseEntity;
    }

    private ResponseEntity<?> getResponseEntity(Map<String, String> param, ResponseEntity<?> responseEntity) {
        if (param.get("title") != null) {
            List<BookInfoResponse> list = bookInfoService.findByTitle(param.get("title"))
                    .stream()
                    .map(BookInfoResponse::new)
                    .collect(Collectors.toList());

            responseEntity = ResponseEntity.ok(new BookInfoListResponse(list));
        }
        if (param.get("id") != null) {
            responseEntity = ResponseEntity.ok(bookInfoService.getBookInfo(Long.parseLong(param.get("id"))));
        }
        if (param.get("title") != null && param.get("id") != null) {
            throw new IllegalArgumentException("param을 하나만 입력해주세요");
        }
        if (param.get("title") == null && param.get("id") == null) {
            throw new IllegalArgumentException("param을 하나 입력해주세요");
        }
        return responseEntity;
    }

    @PutMapping("/{bookinfo-id}")
    public ResponseEntity<BookInfoResponse> updateBookInfoName(
            @RequestBody UpdateBookInfoRequest updateDto,
            @PathVariable(name = "bookinfo-id") Long id
    ) {

        BookInfo bookInfo = bookInfoService.updateName(id, updateDto.getTitle());

        return ResponseEntity.ok(new BookInfoResponse(bookInfo));
    }
}

