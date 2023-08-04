package system.dto;


import lombok.Data;
import system.models.Author;

import java.util.Date;

@Data
public class BookDto {
    private long id;
    private String bookName;
    private Date publishDate;
    private Author authorId;
}
