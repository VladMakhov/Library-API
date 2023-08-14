package system.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String lastName;

    private String nationality;

    private int birthYear;

    private String birthCity;

    private String occupation;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Author(String name, String lastName, String nationality, int birthYear, String birthCity, String occupation) {
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.occupation = occupation;
    }
}
