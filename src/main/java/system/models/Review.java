package system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private int stars;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookId;

    public Review(String description, int stars, Book bookId) {
        this.description = description;
        this.stars = stars;
        this.bookId = bookId;
    }
}
