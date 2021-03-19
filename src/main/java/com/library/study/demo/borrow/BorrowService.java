package com.library.study.demo.borrow;

import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final UserService userService;

    public BorrowDto.Response borrow(Long userId, BorrowDto.Request reqDto) {
        Borrow borrow = new Borrow();
        return borrow.toResponseDto(borrow);
    }
}
