package system.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private long id;
    private String name;
    private String lastName;
    private String nationality;
    private int birthYear;
    private String birthCity;
    private String occupation;
}
