package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.RegisterInstructorDTO;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.service.exceptions.InstructorAlreadyExistException;

import java.util.List;

public interface IInstructorService {
    List<Instructor> getAllInstructors();
    Instructor insertInstructor(RegisterInstructorDTO instructorDTO) throws InstructorAlreadyExistException;
}
