package com.library.study.demo.service;

import com.library.study.demo.dao.BookInfoRepository;
import com.library.study.demo.dao.BookRepository;
import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.BookInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookInfoRepository bookInfoRepository;

    public BookService(BookRepository bookRepository, BookInfoRepository bookInfoRepository) {
        this.bookRepository = bookRepository;
        this.bookInfoRepository = bookInfoRepository;
    }

    @Transactional
    public Long save(Long bookInfoId) {
        BookInfo bookInfo = bookInfoRepository.findById(bookInfoId).orElseThrow(IllegalArgumentException::new);
        return bookRepository.save(new Book(bookInfo)).getId();
    }
}
