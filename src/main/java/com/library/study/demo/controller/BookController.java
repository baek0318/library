package com.library.study.demo.controller;

import com.library.study.demo.domain.Book;
import com.library.study.demo.service.BookService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    public final BookService bookService;
    public final static String registerMessage = "책 등록 성공";
    public final static String deleteMessage = "책 삭제 성공";
    public final static String updateMessage = "책 수정 성공";

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("new")
    public ResponseEntity<String> register(@RequestBody BookDTO bookDTO) {
        bookService.enroll(new Book(bookDTO), bookDTO.getLibraryId());
        return ResponseEntity.ok(registerMessage);
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok(deleteMessage);
    }

    @GetMapping("find/{bookTitle}")
    public ResponseEntity<BookDTO> findOne(@PathVariable String bookTitle) {
        Book book = bookService.findByTitle(bookTitle);
        return ResponseEntity.ok(new BookDTO(book));
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody BookDTO bookDTO) {
      bookService.edit(new Book(bookDTO), bookDTO.getLibraryId());
      return ResponseEntity.ok(updateMessage);
    }

    @PostMapping("borrow")
    public ResponseEntity<Book> borrow(@RequestBody String bookTitle) {
        Book book = bookService.findByTitle(bookTitle);
        return null;
    }
}
