package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.dto.CreateCourseDTO;
import gr.aueb.cf.springfinalproject.dto.CreateCourseSessionDTO;
import gr.aueb.cf.springfinalproject.dto.RegisterInstructorDTO;
import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.service.CourseServiceImpl;
import gr.aueb.cf.springfinalproject.service.CourseSessionServiceImpl;
import gr.aueb.cf.springfinalproject.service.InstructorServiceImpl;
import gr.aueb.cf.springfinalproject.service.exceptions.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdminDashboardController {

    private final CourseSessionServiceImpl courseSessionService;
    private final InstructorServiceImpl instructorService;
    private final CourseServiceImpl courseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/course-sessions")
    public ResponseEntity<?> createCourseSession(@RequestBody CreateCourseSessionDTO dto) {
        try {
            CourseSession createdCourseSession = courseSessionService.createCourseSession(dto);
            return ResponseEntity.ok(createdCourseSession);
        } catch (InstructorNotAvailableException | RoomNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/instructors")
    public ResponseEntity<?> insertInstructor(@Valid @RequestBody RegisterInstructorDTO dto) {
        try {
            Instructor createdInstructor = instructorService.insertInstructor(dto);
            return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
        } catch (InstructorAlreadyExistException e) {
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        try {
            Course createdCourse = courseService.createCourse(createCourseDTO);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (InstructorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/course-sessions/{id}")
    public ResponseEntity<?> deleteCourseSession(@PathVariable Long id) {
        try {
            courseSessionService.deleteCourseSession(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CourseSessionNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
