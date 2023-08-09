package system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.models.dto.AuthorDto;
import system.models.dto.BookDto;
import system.services.interfaces.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/library/")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
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

    @PostMapping("authors/create")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("authors/{authorId}/update")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("authorId") long id, @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("authors/{authorId}/delete")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.ACCEPTED);
    }
}
