package com.library.study.demo.service;

import com.library.study.demo.book.Book;
import com.library.study.demo.book.BookRepository;
import com.library.study.demo.book.BookService;
import com.library.study.demo.book.dto.BookDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryRepository;
import com.library.study.demo.library.LibraryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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
    @Mock
    private LibraryService libraryService;

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
        //given(libraryRepository.findById(any())).willReturn(Optional.of(library));
        given(libraryService.findById(any())).willReturn(library);
        given(bookRepository.save(any())).willReturn(reqDto.toEntity());

        BookDto.Response resDto = bookService.create(1L, reqDto);

        assertThat(resDto.getTitle()).isEqualTo(reqDto.getTitle());
        assertThat(resDto.getAuthor()).isEqualTo(reqDto.getAuthor());
        assertThat(resDto.getIsbn()).isEqualTo(reqDto.getIsbn());
    }

    //TODO :: 0318 서비스 삭제 테스트 알아볼것..
    @Test
    @Disabled
    void 책삭제테스트_정상() {
        //given(bookRepository.deleteById(any()))
    }

    @Test
    void 책하나검색테스트() {
        BookDto.Request reqDto = BookDto.Request.builder()
                .author("톨킨")
                .isbn("123asdf")
                .title("반지의제왕")
                .build();

        Book book = Book.builder()
                .title("반지의제왕")
                .author("톨킨")
                .isbn("123asdf")
                .build();

        Library library = Library.builder()
                .name("정독1")
                .address("서울시1")
                .build();

        library.registerBook(book);

//        given(libraryService.findById(any())).willReturn(library);
        given(bookRepository.findById(any())).willReturn(Optional.of(book));
        BookDto.Response resDto = bookService.find(1L);
        assertReqAndRes(reqDto, resDto);
    }


    @Test
    void multiple_book_get_test() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(Book.builder()
                .author("톨킨")
                .isbn("123asdf")
                .title("반지의제왕")
                .build());
        bookList.add(Book.builder()
                .author("박경리")
                .isbn("1124asdf")
                .title("토지")
                .build());
        bookList.add(Book.builder()
                .author("조앤k롤링")
                .isbn("5435hgf")
                .title("해리포터")
                .build());

        Library library = Library.builder()
                .name("정독1")
                .address("서울시1")
                .build();

        for (Book book : bookList) {
            library.registerBook(book);
        }

        given(libraryService.findById(any())).willReturn(library);

        List<BookDto.Response> resDtoList = bookService.findAll(1L);
        assertThat(resDtoList.size()).isEqualTo(bookList.size());
    }

    private void assertReqAndRes(BookDto.Request reqDto, BookDto.Response resDto) {
        assertThat(resDto.getTitle()).isEqualTo(reqDto.getTitle());
        assertThat(resDto.getAuthor()).isEqualTo(reqDto.getAuthor());
        assertThat(resDto.getIsbn()).isEqualTo(reqDto.getIsbn());
    }
}
