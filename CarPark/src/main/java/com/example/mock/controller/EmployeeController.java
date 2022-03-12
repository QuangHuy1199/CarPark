package com.example.mock.controller;

import com.example.mock.constant.Constant;
import com.example.mock.dto.EmployeeDTO;
import com.example.mock.entity.Employee;
import com.example.mock.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllEmployee() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<EmployeeDTO> employeeList = employeeService.getEmployeeList();
            response.put("employeeList",employeeList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getAllEmployeeWithPaging(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        Map<String, Object> response = new HashMap<>();
        int currentPage = Integer.parseInt(raw_page);
        Pageable pageable = PageRequest.of(currentPage, Constant.DEFAULT_PAGE_SIZE);
        int totalPage = (int) Math.ceil((double) (employeeService.totalEmployee()) / Constant.DEFAULT_PAGE_SIZE);

        try {
            List<EmployeeDTO> employeeList = employeeService.getEmployeeListWithPaging(pageable);
            response.put("employeeList", employeeList);
            response.put("current_page", currentPage);
            response.put("total_page", totalPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param raw_id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        Employee employee;
        try {
            employee = employeeService.getEmployeeById(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     *
     * @param employee
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
        try {
            Employee employeeReturn = employeeService.saveEmployee(employee);
            if (employeeReturn == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param employee
     * @param raw_id
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateNewEmployee(@RequestBody Employee employee, @PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);
        try {
            employee.setEmployeeId(id);
            Employee employeeReturn = employeeService.saveEmployee(employee);
            if (employeeReturn == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param raw_id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Employee> removeEmployee(@PathVariable("id") String raw_id) {
        Long id = Long.parseLong(raw_id);

        try{
            employeeService.removeEmployee(id);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
