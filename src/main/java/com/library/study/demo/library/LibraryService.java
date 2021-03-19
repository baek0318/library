package com.library.study.demo.library;

import com.library.study.demo.library.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LibraryService {
    private final LibraryRepository libraryRepository;

    @Transactional
    public LibraryDto.Response create(LibraryDto.Request reqDto) {
        Library library = libraryRepository.save(reqDto.toEntity());
        return library.toResponseDto();
    }

    @Transactional
    public LibraryDto.Response update(Long libraryId, Map<String, Object> fields) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("해당 도서관이 존재하지 않습니다."));
        // Map key is field name, v is value
        fields.forEach((k, v) -> {
            // use reflection to get field k on manager and set it to value v
            Field field = ReflectionUtils.findField(Library.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, library, v);
        });
        return library.toResponseDto();
    }

    public LibraryDto.Response find(Long libraryId) {
        return findById(libraryId).toResponseDto();
    }

    public List<LibraryDto.Response> findAll() {
        return libraryRepository.findAll().stream()
                .map(Library::toResponseDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("해당 도서관이 존재하지 않습니다."));
        libraryRepository.delete(library);
    }


    public Library findById(Long libraryId) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("해당 도서관이 존재하지 않습니다."));
        return library;
    }
}