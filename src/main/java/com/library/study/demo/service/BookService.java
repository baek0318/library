package com.library.study.demo.service;

import com.library.study.demo.domain.*;
import com.library.study.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final BorrowedBookRepository borrowedBookRepository;


    public BookService(LibraryRepository libraryRepository, BookRepository bookRepository, MemberRepository memberRepository, AdminRepository adminRepository, BorrowedBookRepository borrowedBookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.adminRepository = adminRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    public Book enroll(Book book, Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new IllegalArgumentException("없는 도서관 아이디입니다."));
        return bookRepository.save(new Book(book.getTitle(), book.getAuthor(), library));
    }

    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public List<Book> searchAll() {
        return bookRepository.findAll();
    }

    public Book edit(Book book, Long libraryId){
        Book editBook = findOne(book.getId());
        editBook.setTitle(book.getTitle());
        editBook.setAuthor(book.getAuthor());
        if(book.getLibrary().getId() != libraryId){
            Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new IllegalArgumentException("없는 도서관 아이디입니다."));
            editBook.setLibrary(library);
        }
        return editBook;
    }

    public Book findOne(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 책 아이디입니다."));
    }


    //1. 책의 id로 BookRepository에서 불러오기, findOne() 호출
    //2. 사용자가 Admin인지, Member인지 Instance 확인후에 각자의 Repository에서 findOne()을 해오기
    //3. BorrowBook(book, member, )
    public void borrowBook(Book book, User user) {

        BorrowedBook borrowedBook = new BorrowedBook(book, user, new Date());
        borrowedBookRepository.save(borrowedBook);
        bookRepository.deleteById(book.getId());

    }


}
