package com.library.study.demo.library;

import com.library.study.demo.book.Book;
import com.library.study.demo.library.dto.LibraryDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Book> Books = new ArrayList<Book>();

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
//    public void update(LibraryDto.Request reqDto){
//        if(!reqDto.getAddress().equals("")){
//            address=reqDto.getAddress();
//        }
//        if(!reqDto.getName().equals("")){
//            name=reqDto.getName();
//        }
//    }
}
