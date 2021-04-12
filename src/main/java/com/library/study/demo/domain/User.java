package com.library.study.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "borrow")
    private List<Borrow> borrowList;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
