package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.*;
import com.library.study.demo.domain.Author;
import com.library.study.demo.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("")
    public ResponseEntity<SaveAuthorResponse> save(@RequestBody SaveAuthorRequest saveAuthorRequest) {
        return ResponseEntity.ok(
                authorService.save(saveAuthorRequest.toCommand())
        );
    }

    @GetMapping("")
    public ResponseEntity<AuthorListResponse> getAuthor(@RequestParam String name) {
        List<AuthorResponse> authorList = authorService.getAuthorByName(name)
                .stream()
                .map(it -> new AuthorResponse(it.getId(), it.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthorListResponse(authorList));
    }

    @GetMapping("/{author-id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable(name = "author-id") Long id) {
        Author author = authorService.getAuthorById(id);

        return ResponseEntity.ok(new AuthorResponse(author.getId(), author.getName()));
    }

    @PutMapping("")
    public ResponseEntity<AuthorResponse> updateAuthorName(@RequestBody AuthorUpdateRequest updateDto) {

        Author author = authorService.updateAuthorName(updateDto.getId(), updateDto.getName());

        return ResponseEntity.ok(new AuthorResponse(author.getId(), author.getName()));
    }
}
