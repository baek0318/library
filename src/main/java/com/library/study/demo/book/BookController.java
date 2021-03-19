package com.library.study.demo.book;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final LibraryService libraryService;
    private final BookService bookService;

    @PostMapping("/librarys/{id}/books")
    public ResponseEntity<BookDto.Response> create(@PathVariable("id") Long id,
                                                   @RequestBody BookDto.Request reqDto) {
        BookDto.Response bookDto = bookService.create(id, reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookDto);
    }

    @DeleteMapping("/librarys/{libraryId}/books/{bookId}")
    public ResponseEntity<BookDto.Response> delete(@PathVariable("libraryId") Long libraryId,
                                                   @PathVariable("bookId") Long bookId) {
        bookService.delete(bookId, libraryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/librarys/{libraryId}/books/{bookId}")
    public ResponseEntity<BookDto.Response> find(@PathVariable("libraryId") Long libraryId,
                                                 @PathVariable("bookId") Long bookId) {
        BookDto.Response bookDto = bookService.find(bookId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDto);
    }

    @GetMapping("/librarys/{libraryId}/books")
    public ResponseEntity<List<BookDto.Response>> findAll(@PathVariable("libraryId") Long libraryId) {
        List<BookDto.Response> bookDtoList = bookService.findAll(libraryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookDtoList);
    }
}