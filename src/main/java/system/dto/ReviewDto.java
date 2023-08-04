package system.dto;


import lombok.Data;
import system.models.Book;

@Data
public class ReviewDto {
    private long id;
    private String description;
    private int stars;
    private Book bookId;
}
