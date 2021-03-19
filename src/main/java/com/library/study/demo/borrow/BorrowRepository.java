package com.library.study.demo.borrow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    public Optional<Borrow> findByBook_Id(Long bookID);
}
