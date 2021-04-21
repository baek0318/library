package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.book.BookResponse;
import com.library.study.demo.controller.dto.book.SaveBookRequest;
import com.library.study.demo.controller.dto.book.SaveBookResponse;
import com.library.study.demo.controller.dto.book.UpdateBookRequest;
import com.library.study.demo.domain.Book;
import com.library.study.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<SaveBookResponse> saveBook(@RequestBody SaveBookRequest saveDto) {

        Long id = bookService.save(saveDto.getBookInfoId());

        return ResponseEntity.ok(new SaveBookResponse(id));
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable(name = "book-id") Long id) {

        Book book = bookService.findById(id);

        return ResponseEntity.ok(new BookResponse(book));
    }

    @PutMapping("/{book-id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable(name = "book-id") Long id,
            @RequestBody UpdateBookRequest updateDto
    ) {

        Book updatedBook = bookService.updateBook(id, updateDto.getStatus());

        return ResponseEntity.ok(new BookResponse(updatedBook));
    }
}

/*

 */