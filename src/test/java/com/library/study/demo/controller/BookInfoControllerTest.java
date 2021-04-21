package com.library.study.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.study.demo.controller.dto.bookinfo.SaveBookInfoRequest;
import com.library.study.demo.service.BookInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookInfoController.class)
public class BookInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookInfoService bookInfoService;

    @Test
    void saveTest() throws Exception {

        SaveBookInfoRequest dto = new SaveBookInfoRequest("수학의 정석", 1L);

        given(bookInfoService.save(anyString(), anyLong())).willReturn(1L);

        String content = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/bookinfo/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}