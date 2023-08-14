package system.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import system.models.Author;
import system.models.dto.AuthorDto;
import system.repositorys.AuthorRepository;

import java.util.ArrayList;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;

    private AuthorDto authorDto;

    @BeforeEach
    public void init() {
        author = Author.builder()
                .id(1)
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();

        authorDto = authorService.mapToDto(author);
    }

    @Test
    void createAuthor() {
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
        Assertions.assertNotNull(authorService.createAuthor(authorDto));
    }

    @Test
    void getAllAuthors() {
        Mockito.when(authorRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(authorService.getAllAuthors());
    }

    @Test
    void getAuthorById() {
        Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        Assertions.assertNotNull(authorService.getAuthorById(author.getId()));
    }

    @Test
    void updateAuthor() {
        Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
        Assertions.assertNotNull(authorService.updateAuthor(authorDto.getId(), authorDto));
    }

    @Test
    void deleteAuthor() {
        Mockito.when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        Assertions.assertAll(() -> authorService.deleteAuthor(author.getId()));
    }
}