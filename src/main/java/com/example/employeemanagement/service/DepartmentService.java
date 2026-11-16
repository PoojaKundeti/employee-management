package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Create Department
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get All Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get Department By ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    // Update Department
    public Department updateDepartment(Long id, Department updatedDepartment) {

        Department department = getDepartmentById(id);

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDescription(updatedDepartment.getDescription());

        return departmentRepository.save(department);
    }

    // Delete Department
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
    public List<Department> getDepartmentsByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }

    public List<Department> searchDepartments(String keyword) {
        return departmentRepository.searchDepartments(keyword);
    }
}