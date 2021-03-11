package com.library.study.demo.domain;

import com.library.study.demo.user.User;
import org.junit.jupiter.api.Test;

import static com.library.study.demo.user.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void create() {
        User user = User.builder()
                .loginid("testID")
                .password("testpw1234")
                .role(USER)
                .build();

        assertThat(user.getLoginid()).isEqualTo("testID");
        assertThat(user.getPassword()).isEqualTo("testpw1234");
    }

}
