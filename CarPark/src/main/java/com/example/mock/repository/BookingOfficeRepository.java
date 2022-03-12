package com.example.mock.repository;

import com.example.mock.entity.BookingOffice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingOfficeRepository extends JpaRepository<BookingOffice, Long> {
    @Override
    //@Query(value = "SELECT * ...", nativeQuery = false) //JPQL
    List<BookingOffice> findAll();

    @Override
    Optional<BookingOffice> findById(Long aLong);

    @Override
    BookingOffice save(BookingOffice bookingOffice);

    @Override
    Page<BookingOffice> findAll(Pageable pageable);

    @Override
    long count();
}
