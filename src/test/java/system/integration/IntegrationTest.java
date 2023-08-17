package system.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import system.service.impl.AuthorServiceImpl;
import system.service.impl.BookServiceImpl;
import system.service.impl.ReviewServiceImpl;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class IntegrationTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    public void getReviewById() throws JsonProcessingException {
        String object = objectMapper.writeValueAsString(reviewService.getReviewById(1));
        given()
                .baseUri("http://localhost")
                .port(8080)
                .when()
                .get("/library/books/1/reviews/1")
                .then()
                .statusCode(302)
                .body(equalTo(object));
    }
}
