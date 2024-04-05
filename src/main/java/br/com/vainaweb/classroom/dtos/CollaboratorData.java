package br.com.vainaweb.classroom.dtos;

import br.com.vainaweb.classroom.enums.Role;
import br.com.vainaweb.classroom.model.Address;
import jakarta.validation.Valid;

public record CollaboratorData(
    String name,
    String cpf,
    String email,
    Role role,
    @Valid Address address
) {
}
