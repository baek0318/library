package com.library.study.demo.borrow;

import com.library.study.demo.book.Book;
import com.library.study.demo.book.BookService;
import com.library.study.demo.borrow.dto.BorrowDto;
import com.library.study.demo.library.Library;
import com.library.study.demo.library.LibraryService;
import com.library.study.demo.user.User;
import com.library.study.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final UserService userService;
    private final LibraryService libraryService;
    private final BookService bookService;

    public BorrowDto.Response borrow(Long userId, BorrowDto.Request reqDto) {
        User user = userService.findById(userId);
        Book book = bookService.findById(reqDto.getBookId());
        Library library = libraryService.findById(reqDto.getLibraryId());
        Borrow borrow = borrowRepository.save(new Borrow(user, library, book));
        book.changeStatus();
        return borrow.toResponseDto();
    }

    public void unBorrow(Long userId, BorrowDto.Request reqDto) {
        User user = userService.findById(userId);
        Book book = bookService.findById(reqDto.getBookId());
        Library library = libraryService.findById(reqDto.getLibraryId());
        Borrow borrow = borrowRepository.findByBook_Id(book.getId()).orElseThrow(() -> new RuntimeException("해당 책은 대여중이 아닙니다."));
        user.removeBorrow(borrow);
        library.removeBorrow(borrow);
        borrowRepository.delete(borrow);
        book.changeStatus();
    }
}
