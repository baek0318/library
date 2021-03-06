package com.library.study.demo.service;

import com.library.study.demo.dao.BookInfoRepository;
import com.library.study.demo.dao.BookRepository;
import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.BookInfo;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookInfoRepository bookInfoRepository;

    public BookService(BookRepository bookRepository, BookInfoRepository bookInfoRepository) {
        this.bookRepository = bookRepository;
        this.bookInfoRepository = bookInfoRepository;
    }

    public Long save(Long bookInfoId) {
        BookInfo bookInfo = bookInfoRepository.findById(bookInfoId).orElseThrow(IllegalArgumentException::new);
        return bookRepository.save(new Book(bookInfo)).getId();
    }
}

//
//bookinfo = 1
//book = 1, 2, 3, 4 ,5, 6, 7, 8
//borrow= 1, 2, 3, 4, 5, 6, 7,8