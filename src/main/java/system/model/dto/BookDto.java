package system.model.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long id;
    private String bookName;
    private int year;
    private String author;
}
