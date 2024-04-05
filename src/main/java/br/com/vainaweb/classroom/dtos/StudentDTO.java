package br.com.vainaweb.classroom.dtos;

import br.com.vainaweb.classroom.model.Address;
import jakarta.validation.Valid;

public record StudentDTO(
    String name,
    String cpf,
    String email,
    String phone,
    CourseDTO course,
    @Valid Address address
){}
