package com.library.study.demo.book;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@RequiredArgsConstructor
public class BookController {
    private final LibraryService libraryService;
    private final BookService bookService;

    @PostMapping("/librarys/{id}/books")
    public ResponseEntity<BookDto.Response> create(@PathVariable("id") Long id,
                                                   @RequestBody BookDto.Request reqDto) {
        BookDto.Response bookDto = bookService.create(id, reqDto);
        bookDto.setLibraryId(id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookDto);
    }
}