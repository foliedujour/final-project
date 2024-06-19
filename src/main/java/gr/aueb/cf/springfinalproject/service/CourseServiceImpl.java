package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.CreateCourseDTO;
import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.repository.CourseRepository;
import gr.aueb.cf.springfinalproject.repository.InstructorRepository;
import gr.aueb.cf.springfinalproject.service.exceptions.InstructorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CourseServiceImpl implements ICourseService {


    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Set<Instructor> fetchAllInstructorsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return course.fetchAllInstructors();
    }

    @Override
    public Course createCourse(CreateCourseDTO dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        if (dto.getInstructorIds() != null) {
            for (Long instructorId : dto.getInstructorIds()) {
                Instructor instructor = instructorRepository.findById(instructorId)
                        .orElseThrow(() -> new InstructorNotFoundException(instructorId));
                course.addInstructor(instructor);
            }
        }

        return courseRepository.save(course);
    }


}
