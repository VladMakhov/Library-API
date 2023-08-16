package system.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String bookName;

    private int year;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    private List<Review> reviewList;

    public Book(String bookName, int year, Author author) {
        this.bookName = bookName;
        this.year = year;
        this.author = author;
    }
}
