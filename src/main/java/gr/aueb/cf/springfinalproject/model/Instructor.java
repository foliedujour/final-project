package gr.aueb.cf.springfinalproject.model;


import jakarta.persistence.*;

import lombok.*;
import org.hibernate.validator.constraints.Length;

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


    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true, length = 5)
    @Length(min = 5, max = 5, message = "SSN length must be exactly 5 digits")
    private String ssn;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.PROTECTED)
    private Set<CourseSession> courseSessions = new HashSet<>();

    @ManyToMany(mappedBy = "instructors")
    @Getter(AccessLevel.PROTECTED)
    private Set<Course> courses = new HashSet<>();

    public Instructor(String firstname, String lastname, String ssn) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ssn = ssn;
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
