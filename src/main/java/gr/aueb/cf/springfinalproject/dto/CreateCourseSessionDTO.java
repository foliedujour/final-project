package gr.aueb.cf.springfinalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateCourseSessionDTO {

    @NotNull
    private Long courseId;

    @NotNull
    private Long instructorId;

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

    @NotNull
    private Long roomId;
}
