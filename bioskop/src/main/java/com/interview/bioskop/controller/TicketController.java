package com.interview.bioskop.controller;

import com.interview.bioskop.model.Tickets;
import com.interview.bioskop.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    TicketsRepository ticketsRepository;

    @GetMapping
    ResponseEntity<List<Tickets>> getTickets(){
        return new ResponseEntity<List<Tickets>>((List<Tickets>) ticketsRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/film")
    ResponseEntity<List<Tickets>> getTicketByFilm(@RequestParam("film") String film){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        List<Tickets> ticket = ticketsRepository.findByFilmContaining(film);
        System.out.println(ticket);
        ticket = ticket.stream()
                .filter(q->localDate.compareTo(LocalDate.parse(q.getDate()))<0 || (localTime.compareTo(LocalTime.parse(q.getStartTime()))<=0 && localDate.compareTo(LocalDate.parse(q.getDate()))==0 ))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(ticket);
    }
}
