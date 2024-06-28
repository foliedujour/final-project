package gr.aueb.cf.springfinalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course extends AbstractEntity{

    private String title;

    @Nullable
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.PROTECTED)
    private Set<CourseSession> sessions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "course_instructors",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    )
    private Set<Instructor> instructors = new HashSet<>();


    public Course(String title) {
        this.title = title;
        this.sessions = new HashSet<>();
        this.instructors = new HashSet<>();
    }

    // Convenient methods

    public Set<CourseSession> fetchAllSessions() {
        return Collections.unmodifiableSet(sessions);
    }

    public void addSession(CourseSession session) {
        sessions.add(session);
        session.setCourse(this);
    }

    public Set<Instructor> fetchAllInstructors() { return Collections.unmodifiableSet(instructors); }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        instructor.getCourses().add(this);
    }


}
