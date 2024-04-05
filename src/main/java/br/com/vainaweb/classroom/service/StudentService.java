package br.com.vainaweb.classroom.service;

import br.com.vainaweb.classroom.dtos.StudentData;
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

    public List<Student> register(List<StudentData> students) {
        List<Student> studentEntities = new ArrayList<>();

        for (StudentData studentData : students) {
            Address address = studentData.address();
            address = addressRepository.save(address);

            Student student = new Student(
                    studentData.name(), studentData.cpf(),
                    studentData.email(), studentData.phone(),
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
