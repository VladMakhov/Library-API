package system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import system.dto.AuthorDto;
import system.dto.BookDto;
import system.exceptions.AuthorNotFoundException;
import system.models.Author;
import system.models.Book;
import system.repositorys.AuthorRepository;
import system.repositorys.BookRepository;
import system.services.interfaces.AuthorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookServiceImpl bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, @Lazy BookServiceImpl bookService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> list = authorRepository.findAll();
        return list.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public AuthorDto getAuthorById(long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException("Author with id=" + id + " not found"));
        return mapToDto(author);
    }

    @Override
    public List<BookDto> getBooksByAuthorId(long id) {
        List<Book> list = bookRepository.findAll();
        List<Book> specific = new ArrayList<>();
        for (Book b : list) {
            if (b.getAuthor().getId() == id) {
                specific.add(b);
            }
        }
        return specific.stream()
                .map(bookService::mapToDto)
                .toList();
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setLastName(authorDto.getLastName());
        author.setBirthYear(authorDto.getBirthDate());

        Author newAuthor = authorRepository.save(author);

        return mapToDto(newAuthor);
    }


    public AuthorDto mapToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setLastName(author.getLastName());
        authorDto.setBirthDate(author.getBirthYear());
        return authorDto;
    }
}
