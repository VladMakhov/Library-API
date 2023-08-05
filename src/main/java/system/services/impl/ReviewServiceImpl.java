package system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dto.BookDto;
import system.dto.ReviewDto;
import system.exceptions.BookNotFoundException;
import system.exceptions.ReviewNotFoundException;
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

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

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

    @Override
    public ReviewDto getReviewById(long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("Review with id=" + id + " not found"));
        return mapToDto(review);
    }

    @Override
    public ReviewDto createReview(long id, ReviewDto reviewDto) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book with id=" + id + " not found"));
        Review review = mapToEntity(book, reviewDto);
        Review review1 = reviewRepository.save(review);
        return mapToDto(review1);
    }

    @Override
    public ReviewDto updateReview(long id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("Review with id=" + id + " not found"));
        Book book = bookRepository.findById(review.getBookId().getId()).orElseThrow(() ->
                new BookNotFoundException("Book with id=" + id + " not found"));
        review.setDescription(reviewDto.getDescription());
        review.setStars(reviewDto.getStars());
        review.setBookId(book);
        reviewRepository.save(review);
        return mapToDto(review);
    }

    @Override
    public ReviewDto deleteReview(long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException("Review with id=" + id + " not found"));
        ReviewDto reviewDto = mapToDto(review);
        reviewRepository.delete(review);
        return reviewDto;
    }

    public ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    public Review mapToEntity(Book book, ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setDescription(reviewDto.getDescription());
        review.setStars(reviewDto.getStars());
        review.setBookId(book);
        return review;
    }


}
