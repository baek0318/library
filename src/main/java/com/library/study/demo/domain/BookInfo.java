package com.library.study.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private Author author;

    public BookInfo(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public static Specification<BookInfo> getAll() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static Specification<BookInfo> specByAuthorName(String authorName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), authorName);
    }

    public static Specification<BookInfo> specByTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
