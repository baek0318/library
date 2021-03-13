package com.library.study.demo.controller;

import com.library.study.demo.domain.*;
import com.library.study.demo.service.AdminService;
import com.library.study.demo.service.BookService;
import com.library.study.demo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    public final BookService bookService;
    public final MemberService memberService;
    public final AdminService adminService;

    public final static String registerMessage = "책 등록 성공";
    public final static String deleteMessage = "책 삭제 성공";
    public final static String updateMessage = "책 수정 성공";


    public BookController(BookService bookService, MemberService memberService, AdminService adminService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.adminService = adminService;
    }

    @PostMapping("new")
    public ResponseEntity<String> register(@RequestHeader(value = "isAdmin") boolean isAdmin, @RequestBody BookDTO bookDTO) {
        if(!isAdmin) {
            new IllegalAccessException(ErrorMessage.notAdminMessage);
        }
        bookService.enroll(new Book(bookDTO), bookDTO.getLibraryId());
        return ResponseEntity.ok(registerMessage);
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestHeader(value = "isAdmin") boolean isAdmin, @RequestBody Long bookId) {
        if(!isAdmin) {
            new IllegalAccessException(ErrorMessage.notAdminMessage);
        }
        bookService.delete(bookId);
        return ResponseEntity.ok(deleteMessage);
    }

    @GetMapping("find/{bookTitle}")
    public ResponseEntity<BookDTO> findOne(@RequestHeader(value = "isAdmin") boolean isAdmin, @PathVariable String bookTitle) {
        if(!isAdmin) {
            new IllegalAccessException(ErrorMessage.notAdminMessage);
        }
        Book book = bookService.findByTitle(bookTitle);
        return ResponseEntity.ok(new BookDTO(book));
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@RequestHeader(value = "isAdmin") boolean isAdmin, @RequestBody BookDTO bookDTO) {
        if(!isAdmin) {
            new IllegalAccessException(ErrorMessage.notAdminMessage);
        }
      bookService.edit(new Book(bookDTO), bookDTO.getLibraryId());
      return ResponseEntity.ok(updateMessage);
    }

    @PostMapping("borrow")
    public ResponseEntity<BorrowedBook> borrow(@RequestParam String userId, @RequestBody String bookTitle) {
        //책 이름을 받아서, 이 책들을 조회하고, 그중에서 isBorrowed false인애만 찾고싶음!!
        //책 정보를 다 받아서, isborrowed, 못빌리게 함


        
//        bookService.borrowBook(bookService.findByTitle(),userId);
        return null;
    }
}
