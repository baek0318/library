package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.borrow.BorrowRequest;
import com.library.study.demo.controller.dto.borrow.BorrowResponse;
import com.library.study.demo.service.BorrowService;
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
    public ResponseEntity<BorrowResponse> saveBorrow(
            @PathVariable(name = "user-id") Long id,
            @RequestBody BorrowRequest borrowDto
    ) {

        Long result = borrowService.save(id, borrowDto.getBookId());

        return ResponseEntity.ok(new BorrowResponse(result));
    }
}
