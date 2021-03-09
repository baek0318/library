package com.library.study.demo.repository;

import com.library.study.demo.domain.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
}
