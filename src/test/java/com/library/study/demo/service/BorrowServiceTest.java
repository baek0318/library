package com.library.study.demo.service;

import com.library.study.demo.book.Book;
import com.library.study.demo.book.BookService;
import com.library.study.demo.book.BookStatus;
import com.library.study.demo.borrow.Borrow;
import com.library.study.demo.borrow.BorrowRepository;
import com.library.study.demo.borrow.BorrowService;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryService;
import com.library.study.demo.user.User;
import com.library.study.demo.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.library.study.demo.user.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {
    @Mock
    private BookService bookService;
    @Mock
    private LibraryService libraryService;
    @Mock
    private UserService userService;
    @Mock
    private BorrowRepository borrowRepository;
    @InjectMocks
    private BorrowService borrowService;

    private Book book;
    private Library library;
    private User user;

    @BeforeEach
    void 대여초기화() {
        book = Book.builder()
                .title("Tobi")
                .author("tobi")
                .isbn("101010")
                .build();
        library = Library.builder()
                .name("정독1")
                .address("서울시1")
                .build();
        user = User.builder()
                .loginId("testID")
                .password("testpw1234")
                .name("tester")
                .role(USER)
                .build();

        library.registerBook(book);
    }

    @Test
    void 대여테스트_정상() {
        BorrowDto.Request reqDto = BorrowDto.Request.builder()
                .bookId(book.getId())
                .libraryId(library.getId())
                .build();

        Borrow borrow = new Borrow(user, library, book);

        given(userService.findById(any())).willReturn(user);
        given(libraryService.findById(any())).willReturn(library);
        given(bookService.findById(any())).willReturn(book);
        given(borrowRepository.save(any())).willReturn(borrow);

        BorrowDto.Response resDto = borrowService.borrow(user.getId(), reqDto);

        assertThat(resDto.getUser().getLoginId()).isEqualTo(user.getLoginId());
        assertThat(resDto.getBook().getTitle()).isEqualTo(book.getTitle());
        assertThat(resDto.getBook().getStatus()).isEqualTo(BookStatus.NOT_AVAILABLE);
    }

    @Test
    void 반납테스트_정상() {
        BorrowDto.Request reqDto = BorrowDto.Request.builder()
                .bookId(book.getId())
                .libraryId(library.getId())
                .build();

        given(userService.findById(any())).willReturn(user);
        given(libraryService.findById(any())).willReturn(library);
        given(bookService.findById(any())).willReturn(book);

        Borrow borrow = new Borrow(user, library, book);
        book.changeStatus(); // 대여 상황 가정

        given(borrowRepository.findByBook_Id(any())).willReturn(Optional.of(borrow));

        borrowService.unBorrow(user.getId(), reqDto);

        assertThat(book.getStatus()).isEqualTo(BookStatus.AVAILABLE);

        assertThat(user.getBorrows()).doesNotContain(borrow);
        assertThat(library.getBorrows()).doesNotContain(borrow);
    }
}
