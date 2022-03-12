package com.example.mock.repository;

import com.example.mock.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Override
    List<Trip> findAll();

    @Override
    Optional<Trip> findById(Long aLong);

    @Override
    Trip save(Trip trip);

    @Override
    Page<Trip> findAll(Pageable pageable);

    @Override
    long count();
}
