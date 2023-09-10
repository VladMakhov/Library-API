package system.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest").withReuse(true);

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    public static void afterAll() {
        mySQLContainer.stop();
    }

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

    @Test
    @Order(70)
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
    @Order(80)
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
    @Order(90)
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
    @Order(100)
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
    @Order(110)
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

    @Test
    @Order(120)
    public void getReviewsByBook_returns_list_of_reviews() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/library/books/1/reviews")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isFound(),
                        MockMvcResultMatchers.content().json("""
                                [
                                    {
                                        "id": 1,
                                        "description": "Best book ever",
                                        "stars": 5
                                    },
                                    {
                                        "id": 2,
                                        "description": "Recommend for every one",
                                        "stars": 5
                                    }
                                ]
                            """)
                );
    }

    @Test
    @Order(130)
    public void createReview_returns_review() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/library/books/1/reviews/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "description": "Recommend for every one",
                                        "stars": 5
                                    }
                                                                """))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isCreated(),
                        MockMvcResultMatchers.content().json("""
                                    {
                                        "id": 21,
                                        "description": "Recommend for every one",
                                        "stars": 5
                                    }
                                                                """)
                );
    }

    @Test
    @Order(140)
    public void updateReview_returns_review() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost/library/books/1/reviews/21/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "description": "Recommend for every one",
                                        "stars": 5
                                    }
                                                                """))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        MockMvcResultMatchers.content().json("""
                                    {
                                        "id": 21,
                                        "description": "Recommend for every one",
                                        "stars": 5
                                    }
                                                                """)
                );
    }

    @Test
    @Order(150)
    public void deleteReview_returns_review() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost/library/books/1/reviews/21/delete")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isAccepted(),
                        MockMvcResultMatchers.content().json("""
                                {
                                        "id": 21,
                                        "description": "Recommend for every one",
                                        "stars": 5
                                }
                                """)
                );
    }
}
