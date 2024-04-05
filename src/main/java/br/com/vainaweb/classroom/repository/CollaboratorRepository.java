package br.com.vainaweb.classroom.repository;

import br.com.vainaweb.classroom.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

    Optional<Collaborator> findByEmail(String email);

    Optional<Collaborator> findByCpf(String cpf);
}
