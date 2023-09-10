package system.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import system.config.TestConfig;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(10)
    public void getAllAuthors_returns_list_of_authors() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/authors")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isFound(),
                        MockMvcResultMatchers.content().json("""
                                [
                                {
                                "id": 1,
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                },
                                {
                                "id": 2,
                                "name": "Nikolai",
                                "lastName": "Gogol",
                                "nationality": "Ukrainian",
                                "birthYear": 1809,
                                "birthCity": "Velyki Sorochyntsi",
                                "occupation": "Novelist"
                                },
                                {
                                "id": 3,
                                "name": "Fyodor",
                                "lastName": "Dostoevsky",
                                "nationality": "Russian",
                                "birthYear": 1821,
                                "birthCity": "Moscow",
                                "occupation": "Novelist"
                                },
                                {
                                "id": 4,
                                "name": "Leo",
                                "lastName": "Tolstoy",
                                "nationality": "Russian",
                                "birthYear": 1828,
                                "birthCity": "Yasnaya Polyana",
                                "occupation": "Writer"
                                },
                                {
                                "id": 5,
                                "name": "Mikhail",
                                "lastName": "Lermontov",
                                "nationality": "Russian",
                                "birthYear": 1814,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                ]
                                """)
                );
    }

    @Test
    @Order(20)
    public void getBooksByAuthor_returns_list_of_books() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/authors/1/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isFound(),
                        MockMvcResultMatchers.content().json("""
                                [
                                {
                                "id": 1,
                                "bookName": "War and Peace",
                                "year": 1867,
                                "author": "Alexander Pushkin"
                                },
                                {
                                "id": 2,
                                "bookName": "Anna Karenina",
                                "year": 1877,
                                "author": "Alexander Pushkin"
                                }
                                ]
                                """)
                );
    }

    @Test
    @Order(30)
    public void getAuthorById_returns_author() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/authors/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isFound(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 1,
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                """)
                );
    }

    @Test
    @Order(40)
    public void createAuthor_returns_author() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/library/authors/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                """))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 6,
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                """)
                );
    }

    @Test
    @Order(50)
    public void updateAuthor_returns_author() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost/library/authors/6/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "id": 6,
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                """))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 6,
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                """)
                );
    }

    @Test
    @Order(60)
    public void deleteAuthor_returns_author() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost/library/authors/6/delete")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isAccepted(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 6,
                                "name": "Alexander",
                                "lastName": "Pushkin",
                                "nationality": "Russian",
                                "birthYear": 1799,
                                "birthCity": "Moscow",
                                "occupation": "Poet"
                                }
                                """)
                );
    }

}
