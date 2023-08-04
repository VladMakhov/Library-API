package system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dto.AuthorDto;
import system.models.Author;
import system.repositorys.AuthorRepository;
import system.repositorys.BookRepository;
import system.services.interfaces.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorService;
    private final BookRepository bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorService, BookRepository bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
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
