package com.example.sistlabsolos.dtos.employee;

import java.util.Optional;
import java.util.UUID;

import com.example.sistlabsolos.models.Employee;

public record GetEmployeeByIdDto(
    Optional<Employee> employee,
    UUID labId,
    String errorMessage
) {}
