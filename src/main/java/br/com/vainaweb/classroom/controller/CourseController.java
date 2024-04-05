package br.com.vainaweb.classroom.controller;

import br.com.vainaweb.classroom.dtos.CourseDTO;
import br.com.vainaweb.classroom.model.Course;
import br.com.vainaweb.classroom.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> listAllCourses() {
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        try {
            courseService.update(id, courseDTO);
            return ResponseEntity.ok("Success: Course has been updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: Failed to update the course.");
        }
    }

    @PostMapping
    public ResponseEntity<String> registerCourse(@RequestBody @Valid List<CourseDTO> courseData) {
        try {
            List<Course> courses = courseService.register(courseData);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Success: " + courses.size() + " courses have been registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: Failed to register the course.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.ok("Success: The course has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: Failed to delete the course.");
        }
    }
}
