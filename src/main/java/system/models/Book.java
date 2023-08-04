package system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "publish_date")
    private Date publishDate;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author authorId;

    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    @Column(name = "review_list")
    private List<Review> reviewList;
}
