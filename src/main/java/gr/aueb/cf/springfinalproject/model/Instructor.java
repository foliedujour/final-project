package gr.aueb.cf.springfinalproject.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(mappedBy = "instructors")
    private Set<Course> courses = new HashSet<>();

    public Instructor(String firstName) {
        this.firstName = firstName;
        this.courseSessions = new HashSet<>();
        this.courses = new HashSet<>();
    }

    public Set<CourseSession> fetchAllCourseSessions() {
        return Collections.unmodifiableSet(courseSessions);
    }

    public void addCourseSession(CourseSession courseSession) {
        courseSessions.add(courseSession);
        courseSession.setInstructor(this);
    }

    public Set<Course> fetchAllCourses() { return Collections.unmodifiableSet(courses); }

    public void addCourse(Course course) {
        courses.add(course);
        course.getInstructors().add(this);
    }
}
