package gr.aueb.cf.springfinalproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "courseSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public CourseSession(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.bookings = new HashSet<>();
    }

    public Set<Booking> getAllBookings() {
        return Collections.unmodifiableSet(bookings);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setCourseSession(this);
    }


}
