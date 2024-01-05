package com.example.sistlabsolos.dtos.employee;

import com.example.sistlabsolos.models.Employee;

public record CreateEmployeeResponseDto (
    Employee employee,
    String errorMessage
) {
    
}
