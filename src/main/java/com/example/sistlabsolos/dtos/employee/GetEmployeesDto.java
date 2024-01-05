package com.example.sistlabsolos.dtos.employee;

import java.util.List;

import com.example.sistlabsolos.models.Employee;

public record GetEmployeesDto(
    List<Employee> employees,
    String errorMessage
) {}
