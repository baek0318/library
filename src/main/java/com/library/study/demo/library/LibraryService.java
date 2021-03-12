package com.library.study.demo.library;

import com.library.study.demo.library.dto.LibraryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
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
}
