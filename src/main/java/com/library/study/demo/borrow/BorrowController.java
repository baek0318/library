package com.library.study.demo.borrow;

import com.library.study.demo.borrow.dto.BorrowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping("/users/{userId}/borrow")
    ResponseEntity<BorrowDto.Response> doBorrow(@PathVariable("userId") Long userId,
                                                @RequestBody BorrowDto.Request reqDto) {
        BorrowDto.Response resDto = borrowService.borrow(userId, reqDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDto);
    }
}
