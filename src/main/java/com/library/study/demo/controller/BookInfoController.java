package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.*;
import com.library.study.demo.service.BookInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BookInfoResponse> getBookInfo(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(bookInfoService.getBookInfo(id));
    }
//        save
//        getBookInfoList
//        getBookInfo

}

