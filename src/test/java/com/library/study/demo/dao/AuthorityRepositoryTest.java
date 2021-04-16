package com.library.study.demo.dao;

import com.library.study.demo.domain.Authority;
import com.library.study.demo.domain.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    void testFindByName() {
        //given

        //when
        Authority authority = authorityRepository.findByRole(Role.USER).orElseThrow(IllegalArgumentException::new);

        //then
        Assertions.assertThat(authority.getRole()).isEqualTo(Role.USER);
    }

}
