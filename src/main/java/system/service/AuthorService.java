package system.service;


import system.model.dto.AuthorDto;
import system.model.dto.BookDto;

import java.util.List;

public interface AuthorService {
    List<Object> overview();
    List<AuthorDto> getAllAuthors();
    AuthorDto getAuthorById(long id);
    List<BookDto> getBooksByAuthorId(long id);
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(long id, AuthorDto authorDto);
    AuthorDto deleteAuthor(long id);
}
