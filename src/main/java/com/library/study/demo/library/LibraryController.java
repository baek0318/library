package com.library.study.demo.library;

import com.library.study.demo.library.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping("/librarys")
    public ResponseEntity<LibraryDto.Response> create(@RequestBody LibraryDto.Request reqDto) {
        LibraryDto.Response resDto = libraryService.create(reqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resDto);
    }

    @PatchMapping("/librarys/{id}")
    public ResponseEntity<LibraryDto.Response> update(@PathVariable("id") Long libraryId,
                                                      @RequestBody Map<String, Object> fields) {
        LibraryDto.Response resDto = libraryService.update(libraryId, fields);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDto);
    }

    @GetMapping("/librarys/{id}")
    public ResponseEntity<LibraryDto.Response> find(@PathVariable("id") Long libraryId) {
        LibraryDto.Response resDto = libraryService.find(libraryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDto);
    }

    // TODO :: param 지원
    @GetMapping("/librarys")
    public ResponseEntity<List<LibraryDto.Response>> findall() {
        List<LibraryDto.Response> resDtoList = libraryService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resDtoList);
    }

    @DeleteMapping("/librarys/{id}")
    public ResponseEntity<LibraryDto.Response> remove(@PathVariable("id") Long libraryId) {
        libraryService.delete(libraryId);
        return ResponseEntity.ok()
                .build();
    }
}
