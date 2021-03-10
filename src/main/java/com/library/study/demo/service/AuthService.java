package com.library.study.demo.service;

import com.library.study.demo.domain.User;
import com.library.study.demo.dto.SignUpReqDto;
import com.library.study.demo.dto.SignUpResDto;
import com.library.study.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    @Transactional
    public SignUpResDto signUp(SignUpReqDto signUpReqDto){
        SignUpResDto signUpResDto = new SignUpResDto(10L);

        User savedUser = userRepository.save(signUpReqDto.toEntity());

        return new SignUpResDto(savedUser.getId());
    }
}
