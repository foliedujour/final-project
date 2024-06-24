package gr.aueb.cf.springfinalproject.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking extends AbstractEntity {

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "course_session_id")
    private CourseSession courseSession;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
