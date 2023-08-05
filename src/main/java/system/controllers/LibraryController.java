package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.dto.AuthorDto;
import system.dto.BookDto;
import system.dto.ReviewDto;
import system.services.interfaces.AuthorService;
import system.services.interfaces.BookService;
import system.services.interfaces.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/library/")
public class LibraryController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final ReviewService reviewService;

    @Autowired
    public LibraryController(BookService bookService, AuthorService authorService, ReviewService reviewService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.reviewService = reviewService;
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

    @GetMapping("authors")
    public ResponseEntity<List<AuthorDto>> getAuthorList() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("authors/{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping("authors/{authorId}/books")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.getBooksByAuthorId(id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}/author/books")
    public ResponseEntity<List<BookDto>> getBooksByBook(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getBooksByBook(id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getBookReview(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(reviewService.getReviewByBookId(id), HttpStatus.OK);
    }
}
