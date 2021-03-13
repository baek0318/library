package com.library.study.demo.book;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryService libraryService;

    @Transactional
    public BookDto.Response create(Long libraryId, BookDto.Request reqDto) {
        Library library = libraryService.findById(libraryId);
        Book book = bookRepository.save(reqDto.toEntity());
        library.registerBook(book);
        return book.toResponseDto();
    }
}

