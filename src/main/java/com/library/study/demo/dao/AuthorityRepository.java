package com.library.study.demo.dao;

import com.library.study.demo.domain.Authority;
import com.library.study.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByRole(Role role);
}
