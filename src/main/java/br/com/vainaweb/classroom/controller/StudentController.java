package br.com.vainaweb.classroom.controller;

import br.com.vainaweb.classroom.dtos.StudentDTO;
import br.com.vainaweb.classroom.model.Student;
import br.com.vainaweb.classroom.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> listAllStudents() {
        return studentService.findAll();
    }

    @PostMapping //HTTP POST
    public ResponseEntity<String> registerStudents(@RequestBody List<StudentDTO> students) {
        try {
            List<Student> createdStudents = studentService.register(students);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudents.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to register students.");
        }
    }

    @PutMapping("/{id}") //HTTP PUT
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestParam String name, @RequestParam String cpf, @RequestParam String email, @RequestParam String phone) {
        try {
            studentService.update(id, name, cpf, email, phone);
            return ResponseEntity.status(HttpStatus.OK).body("Success: The HTTP PUT request was processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: The HTTP PUT request was not processed successfully.");
        }
    }

    @DeleteMapping("/{id}") //HTTP DELETE
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Success: The HTTP DELETE request was processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: The HTTP DELETE request was not processed successfully.");
        }
    }

}
