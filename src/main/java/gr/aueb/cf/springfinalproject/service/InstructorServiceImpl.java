package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.RegisterInstructorDTO;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.repository.InstructorRepository;
import gr.aueb.cf.springfinalproject.service.exceptions.InstructorAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InstructorServiceImpl implements IInstructorService {

    private final InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor insertInstructor(RegisterInstructorDTO instructorDTO) throws InstructorAlreadyExistException {
        Optional<Instructor> existingInstructor = instructorRepository.findBySsn(instructorDTO.getSsn());

        if (existingInstructor.isPresent()) {
            throw new InstructorAlreadyExistException(instructorDTO.getSsn());
        }

        Instructor instructor = new Instructor();
        instructor.setFirstname(instructorDTO.getFirstname());
        instructor.setLastname(instructorDTO.getLastname());
        instructor.setSsn(instructorDTO.getSsn());
        return instructorRepository.save(instructor);
    }


}
