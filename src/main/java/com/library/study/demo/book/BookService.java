package com.library.study.demo.book;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class BookService {
    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public BookDto.Response create(Long libraryId, BookDto.Request reqDto) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("해당 도서관이 존재하지 않습니다."));
        Book book = reqDto.toEntity();
        bookRepository.save(book);
        book.registerBook(library);
        return book.toResponseDto();
    }
}

