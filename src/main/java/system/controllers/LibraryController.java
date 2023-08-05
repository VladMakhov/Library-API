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
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.FOUND);
    }

    @GetMapping("books/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.FOUND);
    }

    @GetMapping("books/{bookId}/author")
    public ResponseEntity<AuthorDto> getAuthorByBook(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getAuthorByBookId(id), HttpStatus.FOUND);
    }

    @GetMapping("books/{bookId}/author/books")
    public ResponseEntity<List<BookDto>> getBooksByBook(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getBooksByBook(id), HttpStatus.FOUND);
    }

    @GetMapping("books/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getBookReview(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(reviewService.getReviewByBookId(id), HttpStatus.FOUND);
    }

    @GetMapping("authors")
    public ResponseEntity<List<AuthorDto>> getAuthorList() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.FOUND);
    }

    @GetMapping("authors/{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.FOUND);
    }

    @GetMapping("authors/{authorId}/books")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.getBooksByAuthorId(id), HttpStatus.FOUND);
    }

    @GetMapping("authors/{authorId}/books/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("authorId") long authorId, @PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.FOUND);
    }

    @GetMapping("authors/{authorId}/books/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getBookReview(@PathVariable("authorId") long authorId, @PathVariable("bookId") long id) {
        return new ResponseEntity<>(reviewService.getReviewByBookId(id), HttpStatus.FOUND);
    }

    @GetMapping("books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.FOUND);
    }

    @PostMapping("authors/create")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @PostMapping("authors/{authorId}/books/create")
    public ResponseEntity<BookDto> createBook(@PathVariable("authorId") long id, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(id, bookDto), HttpStatus.CREATED);
    }

    @PostMapping("books/{bookId}/reviews/create")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("bookId") long id, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(id, reviewDto), HttpStatus.CREATED);
    }

    @PutMapping("books/{bookId}/update")
    public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") long id, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDto), HttpStatus.OK);
    }

    @PutMapping("authors/{authorId}/update")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("authorId") long id, @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorDto), HttpStatus.OK);
    }

    @PutMapping("books/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("books/{bookId}/delete")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("authors/{authorId}/delete")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("books/{bookId}/reviews/{reviewId}/delete")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId) {
        return new ResponseEntity<>(reviewService.deleteReview(reviewId), HttpStatus.ACCEPTED);
    }
}
