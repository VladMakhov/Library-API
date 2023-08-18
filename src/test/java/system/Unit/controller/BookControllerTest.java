package system.Unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import system.controller.BookController;
import system.model.Author;
import system.model.Book;
import system.model.dto.BookDto;
import system.repository.AuthorRepository;
import system.repository.BookRepository;
import system.repository.ReviewRepository;
import system.service.AuthorService;
import system.service.BookService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    private Author author;

    private BookDto bookDto;

    @BeforeEach
    void init() {
        author = Author.builder()
                .id(1)
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();

        book = Book.builder()
                .id(1)
                .bookName("Dubrovsky")
                .year(1831)
                .author(author)
                .build();

        bookDto = BookDto.builder()
                .id(1)
                .bookName("Dubrovsky")
                .year(1831)
                .build();
    }

    @Test
    void createBook() throws Exception {
        when(bookService.createBook(author.getId(), bookDto)).thenReturn(bookDto);

        ResultActions response = mockMvc.perform(post("/library/authors/1/books/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName", CoreMatchers.is(bookDto.getBookName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", CoreMatchers.is(bookDto.getYear())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getBookById() throws Exception {
        when(bookService.getBookById(book.getId())).thenReturn(bookDto);

        ResultActions response = mockMvc.perform(get("/library/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDto)));

        response.andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName", CoreMatchers.is(bookDto.getBookName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", CoreMatchers.is(bookDto.getYear())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllBooks() throws Exception {
        List<BookDto> responseDto = Collections.singletonList(bookDto);
        when(bookService.getAllBooks()).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/library/books")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateBook() throws Exception {
        when(bookService.updateBook(bookDto.getId(), bookDto)).thenReturn(bookDto);

        ResultActions response = mockMvc.perform(put("/library/books/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName", CoreMatchers.is(bookDto.getBookName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year", CoreMatchers.is(bookDto.getYear())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteAuthor() throws Exception {
        when(bookService.deleteBook(book.getId())).thenReturn(bookDto);

        ResultActions response = mockMvc.perform(delete("/library/books/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

}