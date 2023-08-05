package system.services.interfaces;

import system.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewByBookId(long id);
}
