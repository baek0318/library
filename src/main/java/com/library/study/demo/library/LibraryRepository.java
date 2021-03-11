package com.library.study.demo.library;

import com.library.study.demo.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
