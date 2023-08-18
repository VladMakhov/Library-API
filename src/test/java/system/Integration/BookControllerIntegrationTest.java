package system.Integration;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import system.model.dto.BookDto;
import system.service.impl.BookServiceImpl;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDto bookDto;

    @BeforeEach
    public void init() {
        bookDto = BookDto.builder()
                .bookName("test")
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
    public void getAllBooks() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(bookService.getAllBooks());
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/books")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }

    @Test
    public void getBookById() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(bookService.getAllBooks().get(0));
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/books/1")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }

    @Test
    public void getBooksByAuthorId() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(bookService.getAuthorByBookId(1));
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/books/1/author")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }

    @Test
    public void getBooksByBook() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(bookService.getBooksByBook(1));
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/books/1/author/books")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }
}
