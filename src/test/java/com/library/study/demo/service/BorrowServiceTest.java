package com.library.study.demo.service;

import com.library.study.demo.dao.BookRepository;
import com.library.study.demo.dao.BorrowRepository;
import com.library.study.demo.dao.UserRepository;
import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.Borrow;
import com.library.study.demo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {

    @Mock
    private BorrowRepository borrowRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BorrowService borrowService;

    /*
        책을 빌리는 로직
        1. 유저가 책정보 목록을 검색한다
        2. 자신이 원하는 목록에 들어간다
        3. 해당 책 정보를 가지는 책 중에 빌릴 수 있는 책이 존재한다면 빌리기 버튼이 활성화가 된다
            **빌릴 수 있는 책인지 확인하는방법**
            3-1. book에 status가 true라면 빌릴 수 있다
            3-2. book에 status가 false라면 빌릴 수 없다
        ---------------------------------------------------------------------------------
        4. 빌리기를 누르면 /borrow/{user-id}  body : borrow-date, book-id;
        5. user-id로 borrow 목록을 검사했을 때 return date가 비어있는게 5개가 넘는다면 못빌림 -> db로 풀어낼지 애플리케이션에서 풀어야할지 고민..
            (현재는 애플리케이션에서 로직으로 풀어낼 예정)
        6. 아니라면 borrow에 로우가 하나 추가, book-id로 책을 검색하여 status 변경
    */

    @Test
    @DisplayName("책을 빌릴 수 없는 경우 테스트")
    void testCannotBorrowBook() {
        //given
        User user = new User("baek", "1234", null);
        Book book = new Book(true, null);
        Borrow borrow1 = new Borrow(book, user);
        Borrow borrow2 = new Borrow(book, user);
        Borrow borrow3 = new Borrow(book, user);
        Borrow borrow4 = new Borrow(book, user);
        Borrow borrow5 = new Borrow(book, user);
        List<Borrow> list = Arrays.asList(borrow1, borrow2, borrow3, borrow4, borrow5);
        given(borrowRepository.findByUserId(anyLong())).willReturn(list);

        //when //then
        Assertions.assertThatThrownBy(() -> borrowService.save(1L, 1L))
                .isInstanceOf(IllegalStateException.class);

    }

    @Test
    @DisplayName("책을 빌릴 수 있는 경우 테스트")
    void testCanBorrowBook() {
        //given
        User user = new User("baek", "1234", null);
        Book book = new Book(true, null);
        Borrow borrow1 = new Borrow(book, user);
        Borrow borrow2 = new Borrow(book, user);
        Borrow borrow3 = new Borrow(book, user);
        Borrow borrow4 = new Borrow(book, user);
        Borrow borrow = new Borrow(book, user);
        borrow.setId(1L);
        List<Borrow> list = Arrays.asList(borrow1, borrow2, borrow3, borrow4);
        given(borrowRepository.findByUserId(anyLong())).willReturn(list);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(book));
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        given(borrowRepository.save(any())).willReturn(borrow);

        //when
        Long result = borrowService.save(1L, 1L);

        //then
        Assertions.assertThat(result).isEqualTo(1L);
    }
}
