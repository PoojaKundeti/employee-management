
package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeRequestDTO;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(EmployeeRequestDTO dto) {

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Employee employee = new Employee();
        employee.setEmployeeName(dto.getEmployeeName());
        employee.setEmail(dto.getEmail());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
    public Employee updateEmployee(Long id, EmployeeRequestDTO dto) {

        Employee employee = getEmployeeById(id);

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        employee.setEmployeeName(dto.getEmployeeName());
        employee.setEmail(dto.getEmail());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }
    public Page<Employee> getEmployeesWithPaginationAndSorting(
            int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return employeeRepository.findAll(PageRequest.of(page, size, sort));

    }
    public List<Employee> getEmployeesByDesignation(String designation) {
        return employeeRepository.findByDesignation(designation);
    }

    public List<Employee> getEmployeesWithSalaryGreaterThan(Double salary) {
        return employeeRepository.findEmployeesWithSalaryGreaterThan(salary);
    }
}
