package com.library.study.demo.service;

import com.library.study.demo.domain.Borrow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {
 /*
    @Mock
    private BorrowRepository borrowRepository;

    @InjectMocks
    private BorrowService borrowService;


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
    @DisplayName("책을 빌릴 수 있는지 테스트")
    void testBorrowBook() {


    }
}
