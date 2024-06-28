package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.service.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Course", description = "Course Management APIs")
public class CourseController {


    private final CourseServiceImpl courseService;

    @GetMapping()
    @Operation(summary = "Retrieves all courses", description = "Fetches a list of all available courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses retrieved successfully", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    })
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}/instructors")
    @Operation(summary = "Retrieves instructors for a specified course", description = "Fetches a list of instructors for the specified course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructors retrieved successfully", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Course not found", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    })
    public Set<Instructor> getInstructorsForCourse(
            @Parameter(description = "ID of the course", required = true)
            @PathVariable Long courseId) {
        return courseService.fetchAllInstructorsForCourse(courseId);
    }
}
