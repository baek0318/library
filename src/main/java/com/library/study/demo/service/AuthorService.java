package com.library.study.demo.service;

import com.library.study.demo.controller.dto.SaveAuthorResponse;
import com.library.study.demo.dao.AuthorRepository;
import com.library.study.demo.domain.Author;
import com.library.study.demo.service.dto.SaveAuthorCommand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public SaveAuthorResponse save(SaveAuthorCommand command) {
        return new SaveAuthorResponse(
                authorRepository.save(new Author(command.getName())).getId()
        );
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

}
