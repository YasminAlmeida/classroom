package br.com.vainaweb.classroom.model;

import br.com.vainaweb.classroom.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "collaborator")
public class Collaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")
    private String email;

    @Column(name = "cpf", unique = true, nullable = false)
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}")
    private String cpf;

    private Role role;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "collaborator")
    private List<Course> courses;

    public Collaborator(String name, String cpf, String email, Role role, Address address) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.role = role;
        this.address = address;
    }
}
