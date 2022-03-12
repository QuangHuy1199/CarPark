package com.example.mock.repository;

import com.example.mock.entity.ParkingLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    @Override
    List<ParkingLot> findAll();

    @Override
    Optional<ParkingLot> findById(Long aLong);

    @Override
    ParkingLot save(ParkingLot parkingLot);

    @Override
    Page<ParkingLot> findAll(Pageable pageable);

    @Override
    long count();
}
