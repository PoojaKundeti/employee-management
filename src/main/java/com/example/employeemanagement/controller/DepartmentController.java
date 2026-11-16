
package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.DepartmentRequestDTO;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department createDepartment(
            @Valid @RequestBody DepartmentRequestDTO dto) {

        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());

        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
    @GetMapping("/search")
    public List<Department> searchDepartments(@RequestParam String keyword) {
        return departmentService.searchDepartments(keyword);
    }

    @GetMapping("/by-name")
    public List<Department> getDepartmentsByName(@RequestParam String name) {
        return departmentService.getDepartmentsByName(name);
    }
}
