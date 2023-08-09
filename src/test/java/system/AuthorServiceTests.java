package system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import system.models.dto.AuthorDto;
import system.models.Author;
import system.repositorys.AuthorRepository;
import system.services.impl.AuthorServiceImpl;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void AuthorService_CreateAuthor_ReturnsAuthorDto() {
        Author author = Author.builder()
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();

        AuthorDto authorDto = AuthorDto.builder()
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();

        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);

        AuthorDto savedAuthor = authorService.createAuthor(authorDto);

        Assertions.assertNotNull(savedAuthor);
    }

}