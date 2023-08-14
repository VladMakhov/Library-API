package system.models.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewDto {
    private long id;
    private String description;
    private int stars;
}
