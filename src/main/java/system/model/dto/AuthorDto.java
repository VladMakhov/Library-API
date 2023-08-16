package system.model.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthorDto {
    private long id;
    private String name;
    private String lastName;
    private String nationality;
    private int birthYear;
    private String birthCity;
    private String occupation;
}
