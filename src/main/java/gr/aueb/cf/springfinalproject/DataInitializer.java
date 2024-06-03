package gr.aueb.cf.springfinalproject;

import gr.aueb.cf.springfinalproject.model.*;
import gr.aueb.cf.springfinalproject.repository.CourseRepository;
import gr.aueb.cf.springfinalproject.repository.CourseSessionRepository;
import gr.aueb.cf.springfinalproject.repository.InstructorRepository;
import gr.aueb.cf.springfinalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Instructor instructor = new Instructor("John");
        Instructor instructor1 = new Instructor("Dorita");
        instructorRepository.save(instructor);
        instructorRepository.save(instructor1);

        Course course = new Course("Yoga Class");
        Course course1 = new Course("Pilates");

        CourseSession session1 = new CourseSession(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        session1.setInstructor(instructor);
        course.addSession(session1);

        CourseSession session2 = new CourseSession(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        session2.setInstructor(instructor);
        course.addSession(session2);

        CourseSession session3 = new CourseSession(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        session3.setInstructor(instructor1);
        course1.addSession(session3);


            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminpassword"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);


        courseRepository.save(course);
        courseRepository.save(course1);






        // No need to save sessions separately since cascade = CascadeType.ALL is set on Course
    }
}
