package com.library.study.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookInfoServiceTest {
/*
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookInfoRepository bookInfoRepository;

    @InjectMocks
    private BookInfoService bookInfoService;

    @Test
    @DisplayName("작가 아이디 유효성 검사")
    void testValidAuthorId() {
        given(authorRepository.findById(anyLong())).willReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> bookInfoService.save("id", 1L))
                .isInstanceOf(IllegalArgumentException.class);

        verify(authorRepository, times(1)).findById(anyLong());
    }

 */
}
