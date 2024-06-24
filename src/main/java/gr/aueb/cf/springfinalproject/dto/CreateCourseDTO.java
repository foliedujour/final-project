package gr.aueb.cf.springfinalproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateCourseDTO {

    @NotBlank(message = "Course title is required")
    private String title;

    private String description;

    private Set<Long> instructorIds;
}
