package com.library.study.demo.repository;

import com.library.study.demo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.library.study.demo.domain.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void 유저저장테스트(){
        User user = User.builder()
                .userId("testID")
                .password("testpw1234")
                .role(USER)
                .build();
        User savedUser = userRepository.save(user);

        Optional<User> one = userRepository.findById(savedUser.getId());
        User findone = one.orElseThrow(()->new RuntimeException());
        assertThat(findone.getUserId()).isEqualTo(user.getUserId());
    }
}
