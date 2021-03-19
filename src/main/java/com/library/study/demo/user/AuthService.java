package com.library.study.demo.user;

import com.library.study.demo.user.dto.SignUpReqDto;
import com.library.study.demo.user.dto.SignUpResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public SignUpResDto signUp(SignUpReqDto signUpReqDto){
        User savedUser = userRepository.save(signUpReqDto.toEntity());
        return new SignUpResDto(savedUser.getId(), savedUser.getName());
    }
}
