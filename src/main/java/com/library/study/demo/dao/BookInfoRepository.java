package com.library.study.demo.dao;

import com.library.study.demo.domain.BookInfo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BookInfoRepository extends JpaRepository<BookInfo, Long>  {
    List<BookInfo> findAll(Specification<BookInfo> specification);
}
