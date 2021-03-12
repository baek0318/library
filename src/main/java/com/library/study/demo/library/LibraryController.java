package com.library.study.demo.library;

import com.library.study.demo.library.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @GetMapping("/librarys/{id}")
//    public ResponseEntity<LibraryDto.Response> find(@PathVariable String libraryId){
//
//    }
//    @GetMapping("/librarys")
//    public ResponseEntity<LibraryDto.Response> findall(@PathVariable String libraryId){
//
//    }
//    @DeleteMapping("/librarys/{id}")
//    public ResponseEntity<LibraryDto.Response> remove(@PathVariable String libraryId){
//
//    }
}
