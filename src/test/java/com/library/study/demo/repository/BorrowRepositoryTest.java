package com.library.study.demo.repository;

import com.library.study.demo.book.Book;
import com.library.study.demo.book.BookRepository;
import com.library.study.demo.borrow.Borrow;
import com.library.study.demo.borrow.BorrowRepository;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryRepository;
import com.library.study.demo.user.User;
import com.library.study.demo.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.library.study.demo.user.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BorrowRepositoryTest {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    void findByBook() {
        User user = User.builder()
                .loginId("testID")
                .password("testpw1234")
                .name("name")
                .role(USER)
                .build();
        Book book = Book.builder()
                .title("Tobi")
                .author("tobi")
                .isbn("101010")
                .build();
        Library library = Library.builder()
                .name("정독1")
                .address("서울시1")
                .build();

        userRepository.save(user);
        bookRepository.save(book);
        libraryRepository.save(library);

        library.registerBook(book);

        Borrow borrow = new Borrow(user, library, book);

        borrowRepository.save(borrow);

        Borrow find = borrowRepository.findByBook_Id(book.getId()).orElseThrow(() -> new RuntimeException("afsadf"));
        assertThat(find).isEqualTo(borrow);
    }
}
