package br.com.vainaweb.classroom.dtos;

import br.com.vainaweb.classroom.model.Address;
import jakarta.validation.Valid;

public record StudentData(
    String name,
    String cpf,
    String email,
    String phone,
    CourseData course,
    @Valid Address address
){}
