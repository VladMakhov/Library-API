package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.dto.AuthorDto;
import system.dto.BookDto;
import system.services.interfaces.BookService;

import java.util.List;

@RestController
@RequestMapping("/library/")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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

    @GetMapping("books/{initialBookId}/author/books/{bookId}")
    public ResponseEntity<BookDto> getBookByBook(@PathVariable("initialBookId") long initialBookId, @PathVariable("bookId") long bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.FOUND);
    }

    @GetMapping("authors/{authorId}/books/{bookId}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("authorId") long authorId, @PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.FOUND);
    }

    @PostMapping("authors/{authorId}/books/create")
    public ResponseEntity<BookDto> createBook(@PathVariable("authorId") long id, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.createBook(id, bookDto), HttpStatus.CREATED);
    }

    @PutMapping("books/{bookId}/update")
    public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") long id, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(id, bookDto), HttpStatus.OK);
    }

    @PutMapping("authors/{authorId}/books/{bookId}/update")
    public ResponseEntity<BookDto> updateBook(@PathVariable("authorId") long authorId, @PathVariable("bookId") long bookId, @RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(bookId, bookDto), HttpStatus.OK);
    }

    @DeleteMapping("books/{bookId}/delete")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("authors/{authorId}/books/{bookId}/delete")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("authorId") long authorId, @PathVariable("bookId") long bookId) {
        return new ResponseEntity<>(bookService.deleteBook(bookId), HttpStatus.ACCEPTED);
    }
}
