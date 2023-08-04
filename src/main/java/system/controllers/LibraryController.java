package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.dto.AuthorDto;
import system.dto.BookDto;
import system.services.interfaces.AuthorService;
import system.services.interfaces.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public LibraryController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("books")
    public ResponseEntity<List<BookDto>> getBookList() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}/author")
    public ResponseEntity<AuthorDto> getAuthorByBook(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getAuthorByBookId(id), HttpStatus.OK);
    }
}
