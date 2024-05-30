package gr.aueb.cf.springfinalproject;

import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.repository.CourseRepository;
import gr.aueb.cf.springfinalproject.repository.CourseSessionRepository;
import gr.aueb.cf.springfinalproject.repository.InstructorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    public DataInitializer(CourseRepository courseRepository,
                           InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Instructor instructor = new Instructor("John");
        instructorRepository.save(instructor);

        Course course = new Course("Yoga Class");

        CourseSession session1 = new CourseSession(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        session1.setInstructor(instructor);
        course.addSession(session1);

        CourseSession session2 = new CourseSession(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        session2.setInstructor(instructor);
        course.addSession(session2);

        courseRepository.save(course);

        // No need to save sessions separately since cascade = CascadeType.ALL is set on Course
    }
}
