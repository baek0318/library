package com.library.study.demo.domain;

import com.library.study.demo.user.User;
import org.junit.jupiter.api.Test;

import static com.library.study.demo.user.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void create() {
        User user = User.builder()
                .loginId("testID")
                .password("testpw1234")
                .role(USER)
                .build();

        assertThat(user.getLoginId()).isEqualTo("testID");
        assertThat(user.getPassword()).isEqualTo("testpw1234");
    }

}
