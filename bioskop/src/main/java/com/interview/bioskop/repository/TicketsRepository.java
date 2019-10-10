package com.interview.bioskop.repository;

import com.interview.bioskop.model.Orders;
import com.interview.bioskop.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketsRepository extends JpaRepository<Tickets, Integer> {
    List<Tickets> findByFilmContaining(String film);
}
