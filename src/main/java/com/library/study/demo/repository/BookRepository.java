package com.library.study.demo.repository;

import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
}
