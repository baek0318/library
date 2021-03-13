package com.library.study.demo.library;

import com.library.study.demo.book.Book;
import com.library.study.demo.library.dto.LibraryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Library {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ID")
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    @Builder
    Library(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public LibraryDto.Response toResponseDto() {
        return LibraryDto.Response.builder()
                .id(id)
                .name(name)
                .address(address)
                .build();
    }

    public void registerBook(Book book) {
        if (book != null) {
            books.add(book);
        }
        book.register(this);
    }
}
