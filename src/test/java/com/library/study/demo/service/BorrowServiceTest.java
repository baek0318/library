package com.library.study.demo.service;

import com.library.study.demo.borrow.BorrowRepository;
import com.library.study.demo.borrow.BorrowService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BorrowServiceTest {
    @Mock
    private BorrowRepository borrowRepository;
    @InjectMocks
    private BorrowService borrowService;
}
