package system.services.interfaces;

import system.dto.AuthorDto;
import system.dto.BookDto;
import system.models.Author;
import system.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(long id);
    AuthorDto getAuthorByBookId(long id);
    List<BookDto> getBooksByBook(long id);
}
