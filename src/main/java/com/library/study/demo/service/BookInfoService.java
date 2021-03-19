package com.library.study.demo.service;


import com.library.study.demo.controller.dto.BookInfoListResponse;
import com.library.study.demo.controller.dto.BookInfoResponse;
import com.library.study.demo.dao.AuthorRepository;
import com.library.study.demo.dao.BookInfoRepository;
import com.library.study.demo.domain.Author;
import com.library.study.demo.domain.BookInfo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookInfoService {
    private final BookInfoRepository bookInfoRepository;
    private final AuthorRepository authorRepository;

    public BookInfoService(BookInfoRepository bookInfoRepository, AuthorRepository authorRepository) {
        this.bookInfoRepository = bookInfoRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Long save(String title, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(IllegalArgumentException::new);

        return bookInfoRepository.save(new BookInfo(title, author)).getId();
    }

    @Transactional
    public BookInfo getBookInfo(Long id) {
        return bookInfoRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<BookInfo> findByTitle(String title) {
        return bookInfoRepository.findAll(BookInfo.specByTitle(title));
    }

    @Transactional
    public List<BookInfo> findByAuthorName(String authorName) {
        return bookInfoRepository.findAll(BookInfo.specByAuthorName(authorName));
    }

    public BookInfoListResponse findAll() {
        List<BookInfoResponse> bookInfoResponseList = bookInfoRepository.findAll()
                .stream()
                .map(BookInfoResponse::new)
                .collect(Collectors.toList());

        return new BookInfoListResponse(bookInfoResponseList);
    }
}
