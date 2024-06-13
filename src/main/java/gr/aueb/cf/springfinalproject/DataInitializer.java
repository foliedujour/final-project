package gr.aueb.cf.springfinalproject;

import gr.aueb.cf.springfinalproject.model.*;
import gr.aueb.cf.springfinalproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoomRepository roomRepository;


    @Override
    public void run(String... args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Athens"));
        Instructor instructor = new Instructor("John");
        Instructor instructor1 = new Instructor("Dorita");
        instructorRepository.save(instructor);
        instructorRepository.save(instructor1);


        Room room = new Room("Room1", 60);
        Room room1 = new Room("Room2", 60);
        Room room2 = new Room("Room3", 60);
        Room room3 = new Room("Room4", 60);
       roomRepository.save(room);
       roomRepository.save(room1);
       roomRepository.save(room2);
       roomRepository.save(room3);



        Course course = new Course("Yoga Class");
        Course course1 = new Course("Pilates");
        course.addInstructor(instructor);
        course.addInstructor(instructor1);


        CourseSession session1 = new CourseSession(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        session1.setInstructor(instructor);
        session1.setRoom(room);
        course.addSession(session1);

        CourseSession session2 = new CourseSession(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        session2.setInstructor(instructor);
        session2.setRoom(room1);
        course.addSession(session2);


        CourseSession session3 = new CourseSession(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        session3.setInstructor(instructor1);
        session3.setRoom(room2);
        course1.addSession(session3);

        CourseSession session4 = new CourseSession(LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        session4.setInstructor(instructor1);
        session4.setRoom(room3);
        course.addSession(session4);


            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);


        courseRepository.save(course);
        courseRepository.save(course1);






        // No need to save sessions separately since cascade = CascadeType.ALL is set on Course
    }
}
