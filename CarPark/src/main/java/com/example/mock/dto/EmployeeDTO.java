package com.example.mock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private String employeeName;
    private Date employeeBirthDate;
    private String employeeAddress;
    private String employeePhone;
    private String department;
}
