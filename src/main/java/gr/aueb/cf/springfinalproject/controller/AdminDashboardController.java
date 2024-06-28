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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Admin Dashboard", description = "Admin Dashboard Management APIs")
public class AdminDashboardController {

    private final CourseSessionServiceImpl courseSessionService;
    private final InstructorServiceImpl instructorService;
    private final CourseServiceImpl courseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/course-sessions")
    @Operation(summary = "Creates a new course session", description = "Creates a new course session with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course session created successfully"),
            @ApiResponse(responseCode = "400", description = "Instructor or Room not available")
    })
    public ResponseEntity<?> createCourseSession(
            @RequestBody CreateCourseSessionDTO dto) {
        try {
            CourseSession createdCourseSession = courseSessionService.createCourseSession(dto);
            return ResponseEntity.ok(createdCourseSession);
        } catch (InstructorNotAvailableException | RoomNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/instructors")
    @Operation(summary = "Registers a new instructor", description = "Registers a new instructor with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Instructor registered successfully"),
            @ApiResponse(responseCode = "409", description = "Instructor already exists")
    })
    public ResponseEntity<?> insertInstructor(
            @Valid @RequestBody RegisterInstructorDTO dto) {
        try {
            Instructor createdInstructor = instructorService.insertInstructor(dto);
            return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
        } catch (InstructorAlreadyExistException e) {
           return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/courses")
    @Operation(summary = "Creates a new course", description = "Creates a new course with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully"),
            @ApiResponse(responseCode = "400", description = "Instructor not found")
    })
    public ResponseEntity<?> createCourse(
            @Valid @RequestBody CreateCourseDTO createCourseDTO) {
        try {
            Course createdCourse = courseService.createCourse(createCourseDTO);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (InstructorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/course-sessions/{id}")
    @Operation(summary = "Deletes a course session", description = "Deletes a course session with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course session deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course session not found")
    })
    public ResponseEntity<?> deleteCourseSession(
            @Parameter(description = "Course session ID", required = true)
            @PathVariable Long id) {
        try {
            courseSessionService.deleteCourseSession(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CourseSessionNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
