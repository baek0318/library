package com.library.study.demo.library;

import com.library.study.demo.library.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;

    @Transactional
    public LibraryDto.CreateResponse create(LibraryDto.CreateRequest reqDto){
        Library library =  libraryRepository.save(reqDto.toEntity());
        return new LibraryDto.CreateResponse(library.getName());
    }
}
