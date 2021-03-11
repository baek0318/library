package com.library.study.demo.library;

import com.library.study.demo.library.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("/librarys")
    public ResponseEntity<LibraryDto.CreateResponse> create(@RequestBody LibraryDto.CreateRequest reqDto){

        LibraryDto.CreateResponse resDto = libraryService.create(reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }
}
