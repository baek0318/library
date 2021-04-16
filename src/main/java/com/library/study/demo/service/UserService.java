package com.library.study.demo.service;

import com.library.study.demo.dao.AuthorityRepository;
import com.library.study.demo.dao.UserRepository;
import com.library.study.demo.domain.Authority;
import com.library.study.demo.domain.Role;
import com.library.study.demo.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public Long save(User user) {

        Authority authority = authorityRepository.findByRole(Role.USER).orElseThrow(IllegalArgumentException::new);
        user.setAuthority(authority);
        User saved = userRepository.save(user);

        return saved.getId();
    }
}
