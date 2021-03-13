package com.library.study.demo.service;

import com.library.study.demo.book.BookRepository;
import com.library.study.demo.book.BookService;
import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private LibraryRepository libraryRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    void 책등록테스트_정상() {
        BookDto.Request reqDto = BookDto.Request.builder()
                .author("톨킨")
                .isbn("123asdf")
                .title("반지의제왕")
                .build();
        Library library = Library.builder()
                .name("정독1")
                .address("서울시1")
                .build();

        given(libraryRepository.findById(any())).willReturn(Optional.of(library));
        given(bookRepository.save(any())).willReturn(reqDto.toEntity());

        BookDto.Response resDto = bookService.create(library.getId(), reqDto);

        assertThat(resDto.getTitle()).isEqualTo(reqDto.getTitle());
        assertThat(resDto.getAuthor()).isEqualTo(reqDto.getAuthor());
        assertThat(resDto.getIsbn()).isEqualTo(reqDto.getIsbn());
    }
}
