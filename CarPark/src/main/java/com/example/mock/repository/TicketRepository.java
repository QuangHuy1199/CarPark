package com.example.mock.repository;

import com.example.mock.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Override
    List<Ticket> findAll();

    @Override
    Optional<Ticket> findById(Long aLong);

    @Override
    Ticket save(Ticket ticket);

    @Override
    Page<Ticket> findAll(Pageable pageable);

    @Override
    long count();
}
