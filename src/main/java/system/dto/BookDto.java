package system.dto;


import lombok.Data;
import system.models.Author;

@Data
public class BookDto {
    private long id;
    private String bookName;
    private int year;
    private String author;
}
