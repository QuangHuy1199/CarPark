package com.example.mock.repository;

import com.example.mock.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    Employee save(Employee employee);

    @Override
    List<Employee> findAll();

    @Override
    Page<Employee> findAll(Pageable pageable);

    @Override
    Optional<Employee> findById(Long aLong);

    @Override
    long count();
}
