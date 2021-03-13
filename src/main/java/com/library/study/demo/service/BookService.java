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
    private final UserRepository userRepository;
    private final BorrowedBookRepository borrowedBookRepository;

    public BookService(LibraryRepository libraryRepository, BookRepository bookRepository, UserRepository userRepository, BorrowedBookRepository borrowedBookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
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


    public BorrowedBook borrowBook(Book book, User user) {
        if(findBorrowedBooks(user.getId()).size() >= 5){
            throw new IllegalArgumentException("빌린 책의 권수가 5권이 넘습니다.");
        }

        Book findBook = findOne(book.getId());
        User findUser = userRepository.findById(user.getId()).orElse(null);
        BorrowedBook borrowedBook = new BorrowedBook(findBook, findUser, new Date());
        borrowedBookRepository.save(borrowedBook);
        bookRepository.deleteById(findBook.getId());

        return borrowedBook;
    }

    public List<BorrowedBook> findBorrowedBooks(String userId){
        return borrowedBookRepository.findByUserId(userId);
    }


}
