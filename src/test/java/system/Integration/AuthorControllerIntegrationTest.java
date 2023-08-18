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
import system.model.dto.AuthorDto;
import system.service.impl.AuthorServiceImpl;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorServiceImpl authorService;


    @Autowired
    private ObjectMapper objectMapper;

    private AuthorDto authorDto;

    @BeforeEach
    public void init() {
        authorDto = AuthorDto.builder()
                .name("test")
                .lastName("test")
                .birthCity("test")
                .birthYear(1)
                .nationality("test")
                .occupation("test")
                .build();
    }


    @Test
    public void createAndDeleteAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/library/authors/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(authorDto.getLastName()))
                .andDo(print());

        List<AuthorDto> list = authorService.getAllAuthors();

        long id = list.get(list.size() - 1).getId();
        String url = "http://localhost/library/authors/" + id + "/delete";

        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(authorDto.getLastName()))
                .andDo(print());
    }

    @Test
    public void updateAuthor() throws Exception {
        AuthorDto author = authorService.getAuthorById(1);

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost/library/authors/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(author.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(author.getLastName()))
                .andDo(print());
    }

    @Test
    public void getBooksByAuthor() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(authorService.getBooksByAuthorId(1));
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/authors/1/books")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }

    @Test
    public void getAllAuthors() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(authorService.getAllAuthors());

        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/authors")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }

    @Test
    public void getAuthorById() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(authorService.getAllAuthors().get(0));
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/authors/1")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }

}
