package br.com.vainaweb.classroom.repository;

import br.com.vainaweb.classroom.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course, Long> {
}
