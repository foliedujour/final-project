package gr.aueb.cf.springfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CourseSessionDTO {
    private Long id;
    private String courseTitle;
    private String description;
    private String instructorName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String roomName;
}
