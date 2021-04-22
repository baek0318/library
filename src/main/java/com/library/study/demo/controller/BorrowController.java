package com.library.study.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/{user-id}")
    public ResponseEntity<?> saveBorrow(
            @PathVariable(name = "user-id") Long id,
            @RequestBody 
    ) {

    }
}
