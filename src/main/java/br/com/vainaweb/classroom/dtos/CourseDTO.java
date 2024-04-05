package br.com.vainaweb.classroom.dtos;

import br.com.vainaweb.classroom.model.Collaborator;
import br.com.vainaweb.classroom.model.Student;

import java.util.List;

public record CourseDTO(
        String name,
        String description,
        Collaborator collaborator,
        List<Student> students
) {
}
