package com.library.study.demo.service;


import com.library.study.demo.domain.Book;
import com.library.study.demo.domain.Library;
import com.library.study.demo.repository.BookRepository;
import com.library.study.demo.repository.BorrowedBookRepository;
import com.library.study.demo.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private List<Book> bookList;
    private Library library;

    private static final Long LIBRARYID = 1L;

    @BeforeAll
    public void setUp() {
        library = new Library();
        libraryRepository.save(library);

        book = new Book("bookTitle", "bookAuthor", library);

        bookList = new LinkedList<>();
        bookList.add(new Book("Tl1", "Au1", library));
        bookList.add(new Book("Tl2", "Au2", library));
        bookList.add(new Book("Tl3", "Au3", library));
        bookList.add(new Book("Tl4", "Au4", library));
        bookList.add(new Book("Tl5", "Au5", library));

        //enroll에서 사용
        when(libraryRepository.findById(LIBRARYID)).thenReturn(Optional.of(library));
        when(bookRepository.save(book)).thenReturn(book);
        //delete에서 사용
        //when(bookRepository.deleteById(book.getId()));
        //searchAll에서 사용
        when(bookRepository.findAll()).thenReturn(bookList);
    }

    @Test
    public void enrollTest() {
        Book enrollBook = bookService.enroll(book, library.getId());
        assertThat(enrollBook.getId(), is(book.getId()));
        assertThat(enrollBook.getTitle(), is(book.getTitle()));
        assertThat(enrollBook.getAuthor(), is(book.getAuthor()));
        assertThat(enrollBook.getLibrary(), is(book.getLibrary()));

    }

    @Test
    public void searchAllTest() {
        assertThat(bookService.searchAll(), is(bookList));
    }

    @Test
    public void deleteTest() {
        bookService.delete(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @Test
    public void editTest() {

    }





}
