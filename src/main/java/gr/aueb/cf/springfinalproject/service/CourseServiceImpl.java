package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
