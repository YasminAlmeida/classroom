package br.com.vainaweb.classroom.service;

import br.com.vainaweb.classroom.dtos.CollaboratorDTO;
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

    public List<Collaborator> register(List<CollaboratorDTO> data) {
        List<Collaborator> collaborators = new ArrayList<>();

        for (CollaboratorDTO collaboratorDTO : data) {
            Address address = collaboratorDTO.address();
            if (address != null) {
                address = addressRepository.save(address);
            }

            Collaborator collaborator = new Collaborator(
                    collaboratorDTO.name(),
                    collaboratorDTO.cpf(),
                    collaboratorDTO.email(),
                    collaboratorDTO.role(),
                    address
            );

            collaborators.add(collaborator);
        }

        return collaboratorRepository.saveAll(collaborators);
    }

    public void update(Long id, CollaboratorDTO collaboratorDTO) {
        Collaborator collaborator = collaboratorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        collaborator.setName(collaboratorDTO.name());
        collaborator.setCpf(collaboratorDTO.cpf());
        collaborator.setEmail(collaboratorDTO.email());
        collaborator.setRole(collaboratorDTO.role());
        collaborator.setAddress(collaboratorDTO.address());

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
