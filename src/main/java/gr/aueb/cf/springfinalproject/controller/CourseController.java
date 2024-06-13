package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.service.CourseServiceImpl;
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
public class CourseController {


    private final CourseServiceImpl courseService;

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}/instructors")
    public Set<Instructor> getInstructorsForCourse(@PathVariable Long courseId) {
        return courseService.fetchAllInstructorsForCourse(courseId);
    }
}
