package com.example.mock.repository;

import com.example.mock.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(String s);

    @Override
    Car save(Car car);

    @Override
    Page<Car> findAll(Pageable pageable);

    @Override
    long count();
}
