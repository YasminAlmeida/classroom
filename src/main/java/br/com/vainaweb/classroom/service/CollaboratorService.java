package br.com.vainaweb.classroom.service;

import br.com.vainaweb.classroom.dtos.CollaboratorData;
import br.com.vainaweb.classroom.model.Address;
import br.com.vainaweb.classroom.model.Collaborator;
import br.com.vainaweb.classroom.repository.AddressRepository;
import br.com.vainaweb.classroom.repository.CollaboratorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository collaboratorRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Collaborator> findAll() {
        return collaboratorRepository.findAll();
    }

    public Collaborator findById(Long id) {
        return collaboratorRepository.findById(id).orElseThrow();
    }

    public List<Collaborator> register(List<CollaboratorData> data) {
        List<Collaborator> collaborators = new ArrayList<>();

        for (CollaboratorData collaboratorData : data) {
            Address address = collaboratorData.address();
            address = addressRepository.save(address);

            Collaborator collaborator = new Collaborator(
                    collaboratorData.name(),
                    collaboratorData.cpf(),
                    collaboratorData.email(),
                    collaboratorData.role(),
                    address
            );

            collaborators.add(collaborator);
        }

        return collaboratorRepository.saveAll(collaborators);
    }

    public void update(Long id, CollaboratorData collaboratorData) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        collaborator.setName(collaboratorData.name());
        collaborator.setCpf(collaboratorData.cpf());
        collaborator.setEmail(collaboratorData.email());
        collaborator.setRole(collaboratorData.role());
        collaborator.setAddress(collaboratorData.address());

        collaboratorRepository.save(collaborator);
    }

    public void delete(Long id) {
        try {
            collaboratorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error: The collaborator could not be deleted.");
        }
    }
}
