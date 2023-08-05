package system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dto.BookDto;
import system.dto.ReviewDto;
import system.exceptions.BookNotFoundException;
import system.models.Book;
import system.models.Review;
import system.repositorys.AuthorRepository;
import system.repositorys.BookRepository;
import system.repositorys.ReviewRepository;
import system.services.interfaces.AuthorService;
import system.services.interfaces.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> getReviewByBookId(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id=" + id + " not found"));
        List<Review> list = book.getReviewList();
        return list.stream()
                .map(this::mapToDto)
                .toList();
    }

    public ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
}
