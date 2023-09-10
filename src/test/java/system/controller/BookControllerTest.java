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
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(10)
    public void getAllBooks_returns_list_of_books() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/library/books")
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
                                },
                                {
                                "id": 3,
                                "bookName": "Dead Souls",
                                "year": 1842,
                                "author": "Nikolai Gogol"
                                },
                                {
                                "id": 4,
                                "bookName": "Taras Bulba",
                                "year": 1835,
                                "author": "Nikolai Gogol"
                                },
                                {
                                "id": 5,
                                "bookName": "The Captains Daughter",
                                "year": 1836,
                                "author": "Fyodor Dostoevsky"
                                },
                                {
                                "id": 6,
                                "bookName": "Dubrovsky",
                                "year": 1831,
                                "author": "Fyodor Dostoevsky"
                                },
                                {
                                "id": 7,
                                "bookName": "Crime and Punishment",
                                "year": 1866,
                                "author": "Leo Tolstoy"
                                },
                                {
                                "id": 8,
                                "bookName": "Demons",
                                "year": 1872,
                                "author": "Leo Tolstoy"
                                },
                                {
                                "id": 9,
                                "bookName": "A Hero of Our Time",
                                "year": 1840,
                                "author": "Mikhail Lermontov"
                                },
                                {
                                "id": 10,
                                "bookName": "Borodino",
                                "year": 1837,
                                "author": "Mikhail Lermontov"
                                }
                                ]
                                """)
                );
    }

    @Test
    @Order(28)
    public void getBookById_returns_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/library/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isFound(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 1,
                                "bookName": "War and Peace",
                                "year": 1867,
                                "author": "Alexander Pushkin"
                                }
                                """)
                );
    }

    @Test
    @Order(20)
    public void createBook_returns_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/library/authors/1/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "bookName": "War and Peace",
                                "year": 1867
                                }
                                """))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 11,
                                "bookName": "War and Peace",
                                "year": 1867,
                                "author": "Alexander Pushkin"
                                }
                                """)
                );
    }

    @Test
    @Order(30)
    public void updateBook_returns_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost/library/books/11/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "bookName": "War and Peace",
                                "year": 1867
                                }
                                """))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 11,
                                "bookName": "War and Peace",
                                "year": 1867,
                                "author": "Alexander Pushkin"
                                }
                                """)
                );
    }

    @Test
    @Order(40)
    public void deleteBook_returns_book() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost/library/books/11/delete")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isAccepted(),
                        MockMvcResultMatchers.content().json("""
                                {
                                "id": 11,
                                "bookName": "War and Peace",
                                "year": 1867,
                                "author": "Alexander Pushkin"
                                }
                                """)
                );
    }

}
