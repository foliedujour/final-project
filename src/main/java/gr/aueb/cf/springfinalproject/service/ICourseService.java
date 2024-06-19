package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.CreateCourseDTO;
import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.Instructor;

import java.util.List;
import java.util.Set;

public interface ICourseService {
    List<Course> getAllCourses();
    Set<Instructor> fetchAllInstructorsForCourse(Long CourseId);
    Course createCourse(CreateCourseDTO dto);
}
