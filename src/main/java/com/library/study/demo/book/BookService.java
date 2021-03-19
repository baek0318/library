package com.library.study.demo.book;

import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public void delete(Long bookId, Long libraryId) {
        Library library = libraryService.findById(libraryId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("해당 도서가 존재하지 않습니다."));
        library.unRegisterBook(book);
        bookRepository.delete(book);
    }

    public BookDto.Response find(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("해당 도서가 존재하지 않습니다."));
//        Book book = library.getBooks()
//                .stream()
//                .filter(b -> b.getId().equals(bookId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("해당 도서가 존재하지 않습니다."));
        return book.toResponseDto();
    }

    public List<BookDto.Response> findAll(Long libraryId) {
        Library library = libraryService.findById(libraryId);
        Set<Book> libraryBooks = library.getBooks();
        return libraryBooks.stream()
                .map(book -> book.toResponseDto())
                .collect(Collectors.toList());
    }
}

