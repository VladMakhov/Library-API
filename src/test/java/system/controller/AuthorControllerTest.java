package system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
import system.controller.AuthorController;
import system.model.Author;
import system.model.dto.AuthorDto;
import system.repository.AuthorRepository;
import system.repository.BookRepository;
import system.repository.ReviewRepository;
import system.service.AuthorService;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

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

    @Autowired
    private ObjectMapper objectMapper;

    private AuthorDto authorDto;

    @BeforeEach
    void init() {
        Author author = Author.builder()
                .id(1)
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();

        authorDto = AuthorDto.builder()
                .id(1)
                .name("Alexander")
                .lastName("Pushkin")
                .nationality("Russian")
                .birthYear(1799)
                .birthCity("Moscow")
                .occupation("Poet")
                .build();
    }

    @Test
    void createAuthor() throws Exception {
        given(authorService.createAuthor(ArgumentMatchers.any())).willAnswer(i -> i.getArgument(0));

        ResultActions response = mockMvc.perform(post("/library/authors/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(authorDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(authorDto.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nationality", CoreMatchers.is(authorDto.getNationality())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllAuthors() throws Exception {
        List<AuthorDto> responseDto = Collections.singletonList(authorDto);
        when(authorService.getAllAuthors()).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/library/authors")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAuthorById() throws Exception {
        when(authorService.getAuthorById(authorDto.getId())).thenReturn(authorDto);

        ResultActions response = mockMvc.perform(get("/library/authors/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(authorDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(authorDto.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nationality", CoreMatchers.is(authorDto.getNationality())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateAuthor() throws Exception {
        when(authorService.updateAuthor(authorDto.getId(), authorDto)).thenReturn(authorDto);

        ResultActions response = mockMvc.perform(put("/library/authors/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(authorDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.is(authorDto.getLastName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nationality", CoreMatchers.is(authorDto.getNationality())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteAuthor() throws Exception {
        when(authorService.deleteAuthor(1)).thenReturn(authorDto);

        ResultActions response = mockMvc.perform(delete("/library/authors/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

}
