package system.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import system.model.Author;
import system.model.Book;
import system.model.Review;
import system.model.dto.ReviewDto;
import system.repository.BookRepository;
import system.repository.ReviewRepository;
import system.service.impl.ReviewServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Book book;

    private Review review;

    private ReviewDto reviewDto;

    @BeforeEach
    public void init() {
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
                .year(2000)
                .author(author)
                .build();

        review = Review.builder()
                .id(1)
                .description("Best book ever")
                .stars(5)
                .bookId(book)
                .build();

        List<Review> list = new ArrayList<>();
        list.add(review);
        book.setReviewList(list);
        reviewDto = reviewService.mapToDto(review);
    }

    @Test
    void getReviewByBookId() {
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));
        List<ReviewDto> expected = book.getReviewList().stream()
                .map(reviewService::mapToDto)
                .toList();
        Assertions.assertEquals(expected, reviewService.getReviewByBookId(book.getId()));
    }

    @Test
    void getReviewById() {
        Mockito.when(reviewRepository.findById(review.getId())).thenReturn(Optional.ofNullable(review));
        Assertions.assertEquals(reviewDto, reviewService.getReviewById(review.getId()));
    }

    @Test
    void createReview() {
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));
        Mockito.when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);
        Assertions.assertEquals(reviewDto, reviewService.createReview(book.getId(), reviewDto));
    }

    @Test
    void updateReview() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.ofNullable(review));
        Assertions.assertEquals(reviewDto, reviewService.updateReview(book.getId(), reviewDto));
    }

    @Test
    void deleteReview() {
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.of(review));
        Assertions.assertAll(() -> reviewService.deleteReview(review.getId()));
    }
}
