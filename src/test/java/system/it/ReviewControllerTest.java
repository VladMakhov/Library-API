package system.it;

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
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(10)
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
    @Order(20)
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
    @Order(30)
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
    @Order(40)
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
