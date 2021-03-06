package com.library.study.demo.service;


import com.library.study.demo.dao.AuthorRepository;
import com.library.study.demo.dao.BookInfoRepository;
import com.library.study.demo.domain.Author;
import com.library.study.demo.domain.BookInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookInfoService {
    private final BookInfoRepository bookInfoRepository;
    private final AuthorRepository authorRepository;

    public BookInfoService(BookInfoRepository bookInfoRepository, AuthorRepository authorRepository) {
        this.bookInfoRepository = bookInfoRepository;
        this.authorRepository = authorRepository;
    }

    public Long save(String title, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(IllegalArgumentException::new);

        return bookInfoRepository.save(new BookInfo(title, author)).getId();
    }

    public BookInfo getBookInfo(Long id) {
        return bookInfoRepository.findById(id).orElse(null);
    }

    public List<BookInfo> findByTitle(String title) {
        return bookInfoRepository.findAll(BookInfo.specByTitle(title));
    }

    public List<BookInfo> findByAuthorName(String authorName) {
        return bookInfoRepository.findAll(BookInfo.specByAuthorName(authorName));
    }
}
