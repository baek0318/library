package com.library.study.demo.service;

import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryRepository;
import com.library.study.demo.library.LibraryService;
import com.library.study.demo.library.dto.LibraryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {
    @InjectMocks
    private LibraryService libraryService;
    @Mock
    private LibraryRepository libraryRepository;

    @Test
    void 라이브러리생성_정상(){
        LibraryDto.CreateRequest reqDto = new LibraryDto.CreateRequest("정독도서관");

        given(libraryRepository.save(any())).willReturn(new Library(reqDto.getName()));

        LibraryDto.CreateResponse resDto = libraryService.create(reqDto);

        assertThat(resDto).isNotNull();
    }
}
