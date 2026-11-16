package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // JPQL Query
    @Query("SELECT d FROM Department d WHERE d.departmentName = :name")
    List<Department> findByDepartmentName(@Param("name") String name);

    // Native SQL Query
    @Query(value = "SELECT * FROM departments WHERE department_name LIKE CONCAT('%', :keyword, '%')",
            nativeQuery = true)
    List<Department> searchDepartments(@Param("keyword") String keyword);
}