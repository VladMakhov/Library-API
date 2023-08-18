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
import system.model.dto.ReviewDto;
import system.service.impl.ReviewServiceImpl;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private ReviewDto reviewDto;

    @BeforeEach
    public void init() {
        reviewDto = ReviewDto.builder()
                .description("test")
                .stars(1)
                .build();
    }

    @Test
    public void updateReview() throws Exception {
        ReviewDto review = reviewService.getReviewById(1);

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost/library/books/1/reviews/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(review.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(review.getStars()))
                .andDo(print());
    }

    @Test
    public void createAndDeleteReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost/library/books/1/reviews/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(reviewDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(reviewDto.getStars()))
                .andDo(print());

        List<ReviewDto> list = reviewService.getAllReviews();

        long id = list.get(list.size() - 1).getId();
        String url = "http://localhost/library/books/1/reviews/" + id + "/delete";

        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(reviewDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stars").value(reviewDto.getStars()))
                .andDo(print());
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
