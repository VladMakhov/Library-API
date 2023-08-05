package system.services.interfaces;


import system.dto.AuthorDto;
import system.dto.BookDto;
import system.models.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    AuthorDto getAuthorById(long id);
    List<BookDto> getBooksByAuthorId(long id);
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthor(long id, AuthorDto authorDto);
    AuthorDto deleteAuthor(long id);
}
