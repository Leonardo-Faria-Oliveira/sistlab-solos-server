package com.example.sistlabsolos.dtos.employee;

import java.util.Optional;

import com.example.sistlabsolos.models.Employee;

public record GetEmployeeByEmailDto(
    Optional<Employee> account,
    String errorMessage
) {}
