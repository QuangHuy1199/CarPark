package com.example.mock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(length = 50)
    private String account;

    @Column(length = 10)
    private String department;

    @Column(length = 50)
    private String employeeAddress;

    @Column
    private Date employeeBirthDate;

    @Column(length = 50)
    private String employeeEmail;

    @Column(length = 50)
    private String employeeName;

    @Column(length = 10)
    private String employeePhone;

    @Column(length = 20)
    private String password;

    @Column(length = 1)
    private String sex;
}
