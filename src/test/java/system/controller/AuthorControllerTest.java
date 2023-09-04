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
import system.service.AuthorService;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthorDto authorDto;

    @BeforeEach
    public void init() {
        authorDto = AuthorDto.builder()
                .name("name")
                .lastName("lastName")
                .birthCity("birthCity")
                .birthYear(1)
                .nationality("nationality")
                .occupation("occupation")
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
    public void getBooksByAuthor() throws Exception {
        List<BookDto> expected = authorService.getBooksByAuthorId(1);
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/authors/1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expected.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].bookName").value(expected.get(0).getBookName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].year").value(expected.get(0).getYear()))
                .andDo(print());
    }

    @Test
    public void getAllAuthors() throws Exception {
        List<AuthorDto> expected = authorService.getAllAuthors();
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(expected.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(expected.get(0).getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName").value(expected.get(0).getLastName()))
                .andDo(print());
    }

    @Test
    public void getAuthorById() throws Exception {
        AuthorDto expected = authorService.getAllAuthors().get(0);
        String object = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expected.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(expected.getLastName()))
                .andDo(print());
    }
}
