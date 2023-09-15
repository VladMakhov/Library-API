package system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.model.dto.AuthorDto;
import system.model.dto.BookDto;
import system.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/library/")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> firstGlance() {
        return new ResponseEntity<>(authorService.overview(), HttpStatus.OK);
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

    @PostMapping("authors/create")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.OK);
    }

    @PutMapping("authors/{authorId}/update")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("authorId") long id, @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("authors/{authorId}/delete")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable("authorId") long id) {
        return new ResponseEntity<>(authorService.deleteAuthor(id), HttpStatus.OK);
    }
}
