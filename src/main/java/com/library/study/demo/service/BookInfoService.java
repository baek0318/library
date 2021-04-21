package com.library.study.demo.service;


import com.library.study.demo.controller.dto.bookinfo.BookInfoListResponse;
import com.library.study.demo.controller.dto.bookinfo.BookInfoResponse;
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
    public BookInfoResponse getBookInfo(Long id) {

        BookInfo bookInfo = bookInfoRepository.findById(id).orElse(null);

        return new BookInfoResponse(bookInfo);
    }

    @Transactional
    public List<BookInfo> findByTitle(String title) {
        return bookInfoRepository.findAll(BookInfo.specByTitle(title));
    }

    @Transactional
    public List<BookInfo> findByAuthorName(String authorName) {
        return bookInfoRepository.findAll(BookInfo.specByAuthorName(authorName));
    }

    @Transactional
    public BookInfoListResponse findAll() {
        List<BookInfoResponse> bookInfoResponseList = bookInfoRepository.findAll()
                .stream()
                .map(BookInfoResponse::new)
                .collect(Collectors.toList());

        return new BookInfoListResponse(bookInfoResponseList);
    }

    @Transactional
    public BookInfo updateName(Long id, String title) {

        BookInfo oldBookInfo = bookInfoRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        oldBookInfo.changeTitle(title);
        return bookInfoRepository.save(oldBookInfo);
    }
}
