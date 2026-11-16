
package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {
        return employeeService.saveEmployee(dto);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return employeeService.updateEmployee(id, dto);
    }
    @GetMapping("/page")
    public Page<Employee> getEmployeesByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "employeeName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return employeeService.getEmployeesWithPaginationAndSorting(
                page, size, sortBy, direction);
    }
    @GetMapping("/designation")
    public List<Employee> getEmployeesByDesignation(
            @RequestParam String designation) {
        return employeeService.getEmployeesByDesignation(designation);
    }

    @GetMapping("/salary")
    public List<Employee> getEmployeesBySalary(@RequestParam Double minSalary) {
        return employeeService.getEmployeesWithSalaryGreaterThan(minSalary);
    }
}
