package br.com.vainaweb.classroom.controller;

import br.com.vainaweb.classroom.dtos.CollaboratorData;
import br.com.vainaweb.classroom.model.Collaborator;
import br.com.vainaweb.classroom.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collaborator")
public class CollaboratorController {

    @Autowired
    private CollaboratorService collaboratorService;

    @GetMapping
    public List<Collaborator> listAllCollaborators() {
        return collaboratorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborator> findCollaboratorById(@PathVariable Long id) {
        Collaborator collaborator = collaboratorService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(collaborator);
    }

    @PostMapping // HTTP POST
    public ResponseEntity<List<Collaborator>> registerCollaborator(@RequestBody List<CollaboratorData> collaboratorDataList) {
        List<Collaborator> createdCollaborators = collaboratorService.register(collaboratorDataList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCollaborators);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCollaborator(@PathVariable Long id, @RequestBody CollaboratorData collaboratorData) {
        collaboratorService.update(id, collaboratorData);
        return ResponseEntity.status(HttpStatus.OK).body("Success: The HTTP PUT request was processed successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCollaborator(@PathVariable Long id) {
        collaboratorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success: The HTTP DELETE request was processed successfully.");
    }
}
