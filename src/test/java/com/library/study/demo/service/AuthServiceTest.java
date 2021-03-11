package com.library.study.demo.service;

import com.library.study.demo.user.AuthService;
import com.library.study.demo.user.Role;
import com.library.study.demo.user.User;
import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import com.library.study.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authservice;
    @Mock
    private UserRepository userRepository;
    @Test
    void 회원가입_정상(){

        SignUpReqDto signUpReqDto = SignUpReqDto.builder()
                .loginid("testId")
                .password("testpw")
                .build();

        User user = User.builder()
                .loginid(signUpReqDto.getLoginid())
                .password(signUpReqDto.getPassword())
                .role(Role.USER)
                .build();

        given(userRepository.save(any()))
                .willReturn(user);

        SignUpResDto signUpResDto = authservice.signUp(signUpReqDto);
        assertThat(signUpResDto).isNotNull();
    }
}
