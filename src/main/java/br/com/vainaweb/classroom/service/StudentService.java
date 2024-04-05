package br.com.vainaweb.classroom.service;

import br.com.vainaweb.classroom.dtos.StudentDTO;
import br.com.vainaweb.classroom.model.Address;
import br.com.vainaweb.classroom.model.Student;
import br.com.vainaweb.classroom.repository.AddressRepository;
import br.com.vainaweb.classroom.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> register(List<StudentDTO> students) {
        List<Student> studentEntities = new ArrayList<>();

        for (StudentDTO studentDTO : students) {
            Address address = studentDTO.address();
            if (address != null) {
                address = addressRepository.save(address);
            }

            Student student = new Student(
                    studentDTO.name(), studentDTO.cpf(),
                    studentDTO.email(), studentDTO.phone(),
                    null, address);
            studentEntities.add(student);
        }

        return studentRepository.saveAll(studentEntities);
    }

    public void update(Long id, String name, String cpf, String email, String phone) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.setName(name);
        student.setCpf(cpf);
        student.setEmail(email);
        student.setPhone(phone);

        studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
