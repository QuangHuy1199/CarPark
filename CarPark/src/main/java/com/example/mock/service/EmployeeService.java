package com.example.mock.service;

import com.example.mock.dto.EmployeeDTO;
import com.example.mock.entity.Employee;
import com.example.mock.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     *
     * @return
     */
    public List<EmployeeDTO> getEmployeeList() {
        List<EmployeeDTO> result = new ArrayList<>();
        try {
            List<Employee> employeeList = employeeRepository.findAll();
            employeeList.forEach(employee -> {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmployeeId(employee.getEmployeeId());
                employeeDTO.setEmployeeName(employee.getEmployeeName());
                employeeDTO.setDepartment(employee.getDepartment());
                employeeDTO.setEmployeeBirthDate(employee.getEmployeeBirthDate());
                employeeDTO.setEmployeeAddress(employee.getEmployeeAddress());
                employeeDTO.setEmployeePhone(employee.getEmployeePhone());

                result.add(employeeDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public List<EmployeeDTO> getEmployeeListWithPaging(Pageable pageable) {
        List<EmployeeDTO> result = new ArrayList<>();
        try {
            List<Employee> employeeList = employeeRepository.findAll(pageable).getContent();
            employeeList.forEach(employee -> {
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmployeeId(employee.getEmployeeId());
                employeeDTO.setEmployeeName(employee.getEmployeeName());
                employeeDTO.setDepartment(employee.getDepartment());
                employeeDTO.setEmployeeBirthDate(employee.getEmployeeBirthDate());
                employeeDTO.setEmployeeAddress(employee.getEmployeeAddress());
                employeeDTO.setEmployeePhone(employee.getEmployeePhone());

                result.add(employeeDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param employee
     * @return
     */
    public Employee saveEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     */
    public void removeEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @return
     */
    public int totalEmployee() {
        return (int) employeeRepository.count();
    }
}
