
package com.example.employeemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    private String designation;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
}
