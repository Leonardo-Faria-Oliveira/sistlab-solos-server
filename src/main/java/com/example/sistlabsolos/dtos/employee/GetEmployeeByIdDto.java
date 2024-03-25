package com.example.sistlabsolos.dtos.employee;

import java.util.Optional;

import com.example.sistlabsolos.models.Employee;

public record GetEmployeeByIdDto(
    Optional<Employee> employee,
    String labName,
    String errorMessage
) {}
