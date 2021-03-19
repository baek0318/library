package com.library.study.demo.controller;

import com.library.study.demo.controller.dto.AuthorResponse;
import com.library.study.demo.controller.dto.SaveAuthorRequest;
import com.library.study.demo.controller.dto.SaveAuthorResponse;
import com.library.study.demo.domain.Author;
import com.library.study.demo.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/save")
    public ResponseEntity<SaveAuthorResponse> save(@RequestBody SaveAuthorRequest saveAuthorRequest) {
        return ResponseEntity.ok(
                authorService.save(saveAuthorRequest.toCommand())
        );
    }

    @GetMapping("")
    public ResponseEntity<AuthorResponse> getAuthor(@RequestParam(name="id") Long authorId) {
        Author author = authorService.getAuthor(authorId);

        return ResponseEntity.ok(new AuthorResponse(author.getId(), author.getName()));
    }
}
