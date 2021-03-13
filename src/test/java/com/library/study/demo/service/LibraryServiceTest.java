package com.library.study.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryRepository;
import com.library.study.demo.library.LibraryService;
import com.library.study.demo.library.dto.LibraryDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {
    private static LibraryDto.Request reqDto;
    @InjectMocks
    private LibraryService libraryService;
    @Mock
    private LibraryRepository libraryRepository;

    @BeforeAll
    static void dto초기화() {
        reqDto = LibraryDto.Request.builder()
                .address("testaddress")
                .name("testLibName")
                .build();
    }

    @Test
    void 도서관생성_정상() {
        given(libraryRepository.save(any())).willReturn(
                Library.builder()
                        .name(reqDto.getName())
                        .address(reqDto.getAddress())
                        .build()
        );

        LibraryDto.Response resDto = libraryService.create(reqDto);

        assertThat(resDto).isNotNull();
    }

    @Test
    void 도서관정보수정_정상() {
        LibraryDto.Request patchReqDto = LibraryDto.Request.builder()
                .address("updatedAddress")
                .name("updatedName")
                .build();

        given(libraryRepository.findById(any())).willReturn(
                Optional.of(reqDto.toEntity())
        );

        ObjectMapper objectMapper = new ObjectMapper();
        Map requestMap = objectMapper.convertValue(patchReqDto, Map.class);
        LibraryDto.Response resDto = libraryService.update(1L, requestMap);

        assertThat(resDto.getAddress()).isEqualTo(patchReqDto.getAddress());
        assertThat(resDto.getName()).isEqualTo(patchReqDto.getName());
    }

    @Test
    void 모든도서관검색_정상() {
        List<Library> libraryList = new ArrayList<>();
        libraryList.add(Library.builder()
                .name("정독1")
                .address("서울시1")
                .build());
        libraryList.add(Library.builder()
                .name("정독2")
                .address("서울시2")
                .build());
        libraryList.add(Library.builder()
                .name("정독3")
                .address("서울시3")
                .build());

        given(libraryRepository.findAll()).willReturn(
                libraryList
        );
        List<LibraryDto.Response> findList = libraryService.findall();
        assertThat(findList.size()).isEqualTo(libraryList.size());
    }
}

