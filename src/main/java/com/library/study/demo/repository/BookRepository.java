package com.library.study.demo.repository;

import com.library.study.demo.domain.Admin;
import com.library.study.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
