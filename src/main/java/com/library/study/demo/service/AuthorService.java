package com.library.study.demo.service;

import com.library.study.demo.dao.AuthorRepository;
import com.library.study.demo.domain.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Long save(String name) {
        return authorRepository.save(new Author(name)).getId();
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

}
