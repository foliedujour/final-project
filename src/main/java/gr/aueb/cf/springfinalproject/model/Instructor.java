package gr.aueb.cf.springfinalproject.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends AbstractEntity {

    private String firstName;

    @Nullable
    private String lastName;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.PROTECTED)
    private Set<CourseSession> courseSessions = new HashSet<>();

    public Instructor(String firstName) {
        this.firstName = firstName;
        this.courseSessions = new HashSet<>();
    }

    public Set<CourseSession> fetchAllCourseSessions() {
        return Collections.unmodifiableSet(courseSessions);
    }

    public void addCourseSession(CourseSession courseSession) {
        courseSessions.add(courseSession);
        courseSession.setInstructor(this);
    }
}
