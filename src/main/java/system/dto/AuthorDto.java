package system.dto;


import lombok.Data;
import system.models.Book;

import java.util.Date;
import java.util.List;

@Data
public class AuthorDto {
    private long id;
    private String name;
    private String lastName;
    private Date birthDate;
    private List<Book> bookList;
}
