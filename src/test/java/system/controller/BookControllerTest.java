package system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import system.model.dto.AuthorDto;
import system.model.dto.BookDto;
import system.service.BookService;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDto bookDto;

    @BeforeEach
    public void init() {
        bookDto = BookDto.builder()
                .bookName("bookName")
                .year(1)
                .build();
    }

    @Test
    public void updateBook() throws Exception {
        BookDto book = bookService.getBookById(1);

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost/library/books/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value(book.getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(book.getYear()))
                .andDo(print());
    }

    @Test
    public void createAndDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/library/authors/1/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value(bookDto.getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(bookDto.getYear()))
                .andDo(print());

        List<BookDto> list = bookService.getAllBooks();
        long id = list.get(list.size() - 1).getId();
        String url = "http://localhost/library/authors/1/books/" + id + "/delete";

        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value(bookDto.getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(bookDto.getYear()))
                .andDo(print());
    }

    @Test
    public void getAllBooks() throws Exception {
        List<BookDto> expected = bookService.getAllBooks();
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/library/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expected.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bookName").value(expected.get(0).getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].year").value(expected.get(0).getYear()))
                .andDo(print());
    }

    @Test
    public void getBookById() throws Exception {
        BookDto expected = bookService.getBookById(1);
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/library/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value(expected.getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(expected.getYear()))
                .andDo(print());
    }

    @Test
    public void getAuthorByBookId() throws Exception {
        AuthorDto expected = bookService.getAuthorByBookId(1);
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/library/books/1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(expected.getLastName()))
                .andDo(print());
    }

    @Test
    public void getBooksByBook() throws Exception {
        List<BookDto> expected = bookService.getBooksByBook(1);
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/books/1/author/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bookName").value(expected.get(0).getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].year").value(expected.get(0).getYear()))
                .andDo(print());
    }
}
