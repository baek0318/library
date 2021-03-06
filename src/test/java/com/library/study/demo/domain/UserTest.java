package com.library.study.demo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.library.study.demo.domain.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void create() {
        User user = User.builder()
                .userId("testID")
                .password("testpw1234")
                .role(USER)
                .build();

        assertThat(user.getUserId()).isEqualTo("testID");
        assertThat(user.getPassword()).isEqualTo("testpw1234");
    }

}
