package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.borrow.*;
import com.library.study.demo.domain.Borrow;
import com.library.study.demo.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrow")
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

    @GetMapping("/{borrow-id}")
    public ResponseEntity<BorrowInfoResponse> getBorrowInfo(@PathVariable(name = "borrow-id") Long id) {

        Borrow borrow = borrowService.getBorrowInfo(id);

        return ResponseEntity.ok(new BorrowInfoResponse(borrow));
    }

    @GetMapping("/{user-id}/list")
    public ResponseEntity<BorrowInfoListResponse> getBorrowInfoList(@PathVariable(name = "user-id") Long id) {

        List<Borrow> borrowList = borrowService.getBorrowInfoList(id);

        return ResponseEntity.ok(
                new BorrowInfoListResponse(
                        borrowList
                                .stream()
                                .map(BorrowInfoResponse::new)
                                .collect(Collectors.toList())
                )
        );
    }

    @PutMapping("/{borrow-id}")
    public ResponseEntity<BorrowResponse> updateReturnDate(
            @PathVariable(name = "borrow-id") Long id,
            @RequestBody UpdateBorrowRequest updateBorrowDto
    ) {

        Long result = borrowService.updateReturnDate(id, LocalDate.parse(updateBorrowDto.getDate()));

        return ResponseEntity.ok(new BorrowResponse(result));
    }
}
