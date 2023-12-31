package system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.model.dto.ReviewDto;
import system.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/library/")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("books/{initialBookId}/author/books/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByBookByBook(@PathVariable("initialBookId") long initialBookId, @PathVariable("bookId") long bookId) {
        return new ResponseEntity<>(reviewService.getReviewByBookId(bookId), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getBookReviews(@PathVariable("bookId") long id) {
        return new ResponseEntity<>(reviewService.getReviewByBookId(id), HttpStatus.OK);
    }

    @GetMapping("authors/{authorId}/books/{bookId}/reviews")
    public ResponseEntity<List<ReviewDto>> getBookReviews(@PathVariable("authorId") long authorId, @PathVariable("bookId") long id) {
        return new ResponseEntity<>(reviewService.getReviewByBookId(id), HttpStatus.OK);
    }

    @GetMapping("books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
    }

    @PostMapping("books/{bookId}/reviews/create")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("bookId") long id, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(id, reviewDto), HttpStatus.OK);
    }

    @PostMapping("authors/{authorId}/books/{bookId}/reviews/create")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("authorId") long authorId, @PathVariable("bookId") long bookId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(bookId, reviewDto), HttpStatus.OK);
    }

    @PutMapping("books/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewDto), HttpStatus.OK);
    }

    @PutMapping("authors/{authorId}/books/{bookId}/reviews/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("authorId") long authorId, @PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("books/{bookId}/reviews/{reviewId}/delete")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId) {
        return new ResponseEntity<>(reviewService.deleteReview(reviewId), HttpStatus.OK);
    }

    @DeleteMapping("authors/{authorId}books/{bookId}/reviews/{reviewId}/delete")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable("authorId") long authorId, @PathVariable("bookId") long bookId, @PathVariable("reviewId") long reviewId) {
        return new ResponseEntity<>(reviewService.deleteReview(reviewId), HttpStatus.OK);
    }
}
