package com.library.study.demo.service;

import com.library.study.demo.book.Book;
import com.library.study.demo.borrow.Borrow;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.user.Role;
import com.library.study.demo.user.User;
import com.library.study.demo.user.UserRepository;
import com.library.study.demo.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    void 사용자대여중도서조회_정상() {
        User user = User.builder()
                .loginId("testUser")
                .password("testPw")
                .name("testName")
                .role(Role.USER)
                .build();
        Book book1 = Book.builder()
                .title("반지의제왕")
                .author("톨킨")
                .isbn("123asdf")
                .build();
        Book book2 = Book.builder()
                .author("조앤k롤링")
                .isbn("5435hgf")
                .title("해리포터")
                .build();
        Library library = Library.builder()
                .name("정독1")
                .address("서울시1")
                .build();

        library.registerBook(book1);
        library.registerBook(book2);

        Borrow borrow1 = new Borrow(user, library, book1);
        Borrow borrow2 = new Borrow(user, library, book2);

        given(userRepository.findById(any())).willReturn(Optional.of(user));
        List<BorrowDto.Response> findBorrowings = userService.getBorrowings(1L);

        assertThat(findBorrowings.size()).isEqualTo(2);
    }
}
