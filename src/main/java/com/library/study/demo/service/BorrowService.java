package com.library.study.demo.service;

import com.library.study.demo.dao.BookRepository;
import com.library.study.demo.dao.BorrowRepository;
import com.library.study.demo.dao.UserRepository;
import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.Borrow;
import com.library.study.demo.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Long save(Long userId, Long bookId) {

        isBorrow(borrowRepository.findByUserId(userId));
        Book book = bookRepository.findById(bookId).orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        Borrow borrow = new Borrow(book, user);
        borrow.setBorrowDate(LocalDate.now());
        Borrow borrowed = borrowRepository.save(borrow);

        return borrowed.getId();
    }

    private void isBorrow(List<Borrow> borrowList) {
        long result = borrowList
                .stream()
                .filter(it -> it.getReturnDate() == null)
                .count();

        if(result == 5L) {
            throw new IllegalStateException("더이상 빌릴 수 없습니다");
        }
    }

    public Borrow getBorrowInfo(Long id) {
        return borrowRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("존재하지 않는 borrow-id 입니다")
                );
    }

    public List<Borrow> getBorrowInfoList(Long userId) {
        return borrowRepository.findByUserId(userId);
    }

    public Long updateReturnDate(Long id, LocalDate date) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하지 않는 borrow-id 입니다"));
        borrow.updateReturnDate(date);
        return borrowRepository.save(borrow).getId();
    }
}
