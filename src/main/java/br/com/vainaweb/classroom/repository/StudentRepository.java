package br.com.vainaweb.classroom.repository;

import br.com.vainaweb.classroom.model.Student;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(@Nullable String email);

    Optional<Student> findByCpf(@Nullable String cpf);
}
