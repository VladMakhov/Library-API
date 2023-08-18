package system.Unit.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import system.model.Author;
import system.model.Book;
import system.model.dto.BookDto;
import system.repository.AuthorRepository;
import system.repository.BookRepository;
import system.service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    private Author author;

    private BookDto bookDto;

    @BeforeEach
    public void init() {
        author = Author.builder()
                .id(1)
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();

        book = Book.builder()
                .id(1)
                .bookName("Dubrovsky")
                .year(2000)
                .author(author)
                .build();

        bookDto = bookService.mapToDto(book);
    }

    @Test
    void getAllBooks() {
        List<Book> get = new ArrayList<>();
        get.add(book);
        when(bookRepository.findAll()).thenReturn(get);
        List<BookDto> expected = new ArrayList<>();
        expected.add(bookDto);
        Assertions.assertEquals(expected, bookService.getAllBooks());
    }

    @Test
    void createBook() {
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Assertions.assertEquals(bookDto, bookService.createBook(author.getId(), bookDto));
    }

    @Test
    void updateBook() {
        when(bookRepository.findById(author.getId())).thenReturn(Optional.ofNullable(book));
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(author));
        Assertions.assertEquals(bookDto, bookService.updateBook(author.getId(), bookDto));
    }

    @Test
    void deleteBook() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Assertions.assertAll(() -> bookService.deleteBook(book.getId()));
    }

    @Test
    void getBookById() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));
        Assertions.assertNotNull(bookService.getBookById(book.getId()));
    }
}
