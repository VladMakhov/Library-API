package system.controllers;

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
import system.models.Author;
import system.models.Book;
import system.models.Review;
import system.models.dto.ReviewDto;
import system.repositorys.AuthorRepository;
import system.repositorys.BookRepository;
import system.repositorys.ReviewRepository;
import system.services.AuthorService;
import system.services.BookService;
import system.services.ReviewService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(ReviewController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

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

    @MockBean
    private ReviewService reviewService;
    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    private Review review;

    private ReviewDto reviewDto;

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

        book = Book.builder()
                .id(1)
                .bookName("Dubrovsky")
                .year(1831)
                .author(author)
                .build();

        review = Review.builder()
                .id(1)
                .description("Best book ever")
                .stars(5)
                .bookId(book)
                .build();

        reviewDto = ReviewDto.builder()
                .id(1)
                .description("Best book ever")
                .stars(5)
                .build();
    }
    @Test
    void getBookReview() throws Exception {
        List<ReviewDto> responseDto = Collections.singletonList(reviewDto);
        when(reviewService.getReviewByBookId(book.getId())).thenReturn(responseDto);

        ResultActions response = mockMvc.perform(get("/library/books/1/reviews")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getReviewById() throws Exception {
        when(reviewService.getReviewById(review.getId())).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(get("/library/books/1/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(reviewDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars", CoreMatchers.is(reviewDto.getStars())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void createReview() throws Exception {
        when(reviewService.createReview(book.getId(), reviewDto)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(post("/library/books/1/reviews/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(reviewDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars", CoreMatchers.is(reviewDto.getStars())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateReview() throws Exception {
        when(reviewService.updateReview(book.getId(), reviewDto)).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(put("/library/books/1/reviews/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(reviewDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars", CoreMatchers.is(reviewDto.getStars())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteReview() throws Exception {
        when(reviewService.deleteReview(review.getId())).thenReturn(reviewDto);

        ResultActions response = mockMvc.perform(delete("/library/books/1/reviews/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}