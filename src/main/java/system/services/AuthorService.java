package system.services;


import system.models.dto.AuthorDto;
import system.models.dto.BookDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    AuthorDto getAuthorById(long id);
    List<BookDto> getBooksByAuthorId(long id);
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(long id, AuthorDto authorDto);
    AuthorDto deleteAuthor(long id);
}
