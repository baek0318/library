package com.library.study.demo.service;

import com.library.study.demo.dao.UserRepository;
import com.library.study.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public Long save(User user) {


        User saved = userRepository.save(user);

        return saved.getId();
    }
}
