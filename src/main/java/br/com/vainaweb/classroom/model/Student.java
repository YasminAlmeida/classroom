package br.com.vainaweb.classroom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public Student(String name, String cpf, String email, String phone, List<Course> courses, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.courses = courses;
        this.address = address;
    }
}
