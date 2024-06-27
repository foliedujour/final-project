package gr.aueb.cf.springfinalproject.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSession extends AbstractEntity {

    @Nonnull
    private LocalDateTime startDateTime;

    @Nonnull
    private LocalDateTime endDateTime;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "courseSession", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.PROTECTED)
    private Set<Booking> bookings = new HashSet<>();

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;



    public CourseSession(@Nonnull LocalDateTime startDateTime,
                         @Nonnull LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.bookings = new HashSet<>();
    }

    public Set<Booking> fetchAllBookings() {
        return Collections.unmodifiableSet(bookings);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setCourseSession(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }


}
