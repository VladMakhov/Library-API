package system.services;

import system.models.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewByBookId(long id);
    ReviewDto getReviewById(long id);
    ReviewDto createReview(long id, ReviewDto reviewDto);
    ReviewDto updateReview(long id, ReviewDto reviewDto);
    ReviewDto deleteReview(long id);
}
