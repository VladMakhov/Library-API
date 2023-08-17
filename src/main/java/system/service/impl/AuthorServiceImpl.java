package system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import system.model.dto.AuthorDto;
import system.model.dto.BookDto;
import system.exception.notFoundException.AuthorNotFoundException;
import system.model.Author;
import system.repository.AuthorRepository;
import system.repository.BookRepository;
import system.service.AuthorService;

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
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().getId() == id)
                .map(bookService::mapToDto)
                .toList();
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setLastName(authorDto.getLastName());
        author.setNationality(authorDto.getNationality());
        author.setBirthYear(authorDto.getBirthYear());
        author.setBirthCity(authorDto.getBirthCity());
        author.setOccupation(authorDto.getOccupation());
        Author newAuthor = authorRepository.save(author);
        return mapToDto(newAuthor);
    }

    @Override
    public AuthorDto updateAuthor(long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException("Author with id=" + id + " not found"));
        author.setName(authorDto.getName());
        author.setLastName(authorDto.getLastName());
        author.setNationality(authorDto.getNationality());
        author.setBirthYear(authorDto.getBirthYear());
        author.setBirthCity(authorDto.getBirthCity());
        author.setOccupation(authorDto.getOccupation());
        authorRepository.save(author);
        return mapToDto(author);
    }

    @Override
    public AuthorDto deleteAuthor(long id) {
        Author author = authorRepository.findById(id).orElseThrow(() ->
                new AuthorNotFoundException("Author with id=" + id + " not found"));
        AuthorDto authorDto = mapToDto(author);
        authorRepository.delete(author);
        return authorDto;
    }

    public AuthorDto mapToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        authorDto.setLastName(author.getLastName());
        authorDto.setNationality(author.getNationality());
        authorDto.setBirthYear(author.getBirthYear());
        authorDto.setBirthCity(author.getBirthCity());
        authorDto.setOccupation(author.getOccupation());
        return authorDto;
    }
}
