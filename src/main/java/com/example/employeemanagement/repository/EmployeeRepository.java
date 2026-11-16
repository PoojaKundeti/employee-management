package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JPQL Query
    @Query("SELECT e FROM Employee e WHERE e.designation = :designation")
    List<Employee> findByDesignation(@Param("designation") String designation);

    // Native SQL Query
    @Query(value = "SELECT * FROM employees WHERE salary > :salary",
            nativeQuery = true)
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);


}