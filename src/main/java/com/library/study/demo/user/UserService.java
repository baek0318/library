package com.library.study.demo.user;

import com.library.study.demo.borrow.Borrow;
import com.library.study.demo.borrow.dto.BorrowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        return user;
    }

    public List<BorrowDto.Response> getBorrowings(Long userId) {
        User user = findById(userId);
        List<BorrowDto.Response> list = new ArrayList<>();
        for (Borrow borrow : user.getBorrows()) {
            BorrowDto.Response resDto = borrow.toResponseDto();
            list.add(resDto);
        }
        return list;
    }
}
