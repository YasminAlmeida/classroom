package br.com.vainaweb.classroom.service;

import br.com.vainaweb.classroom.dtos.CourseDTO;
import br.com.vainaweb.classroom.model.Collaborator;
import br.com.vainaweb.classroom.model.Course;
import br.com.vainaweb.classroom.model.Student;
import br.com.vainaweb.classroom.repository.CollaboratorRepository;
import br.com.vainaweb.classroom.repository.CourseRepository;
import br.com.vainaweb.classroom.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CollaboratorRepository collaboratorRepository;

    public List<Course> register(List<CourseDTO> courseData) {
        List<Course> courses = courseData.stream().map(data -> {
            Collaborator collaborator = collaboratorRepository.findById(data.collaborator().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

            List<Student> students = studentRepository.findAllById(data.students().stream().map(Student::getId).collect(Collectors.toList()));

            return new Course(data.name(), data.description(), collaborator, students);
        }).collect(Collectors.toList());

        return courseRepository.saveAll(courses);
    }

    public void update(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));

        course.setName(courseDTO.name());
        course.setDescription(courseDTO.description());

        if (courseDTO.collaborator() != null) {
            Collaborator collaborator = collaboratorRepository.findById(courseDTO.collaborator().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));
            course.setCollaborator(collaborator);
        }

        if (courseDTO.students() != null && !courseDTO.students().isEmpty()) {
            List<Student> students = studentRepository.findAllById(courseDTO.students().stream().map(Student::getId).collect(Collectors.toList()));
            course.setStudents(students);
        }

        courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
