package com.library.study.demo.service;

import com.library.study.demo.controller.dto.author.SaveAuthorResponse;
import com.library.study.demo.dao.AuthorRepository;
import com.library.study.demo.domain.Author;
import com.library.study.demo.service.dto.SaveAuthorCommand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public List<Author> getAuthorByName(String name) {
        return authorRepository.findByNameLike("%"+name+"%");
    }

    @Transactional
    public Author updateAuthorName(Long id, String changing) {
        Author author = authorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        author.changeName(changing);
        return authorRepository.save(author);
    }
}
