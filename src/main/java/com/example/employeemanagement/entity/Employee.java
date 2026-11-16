
package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false, unique = true)
    private String email;

    private String designation;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
