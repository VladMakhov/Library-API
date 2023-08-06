package system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String lastName;

    private String Nationality;

    private int birthYear;

    private String birthCity;

    private String Occupation;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Author(String name, String lastName, String nationality, int birthYear, String birthCity, String occupation) {
        this.name = name;
        this.lastName = lastName;
        this.Nationality = nationality;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.Occupation = occupation;
    }
}
