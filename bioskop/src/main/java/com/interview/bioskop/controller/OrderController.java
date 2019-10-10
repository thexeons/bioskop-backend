package com.interview.bioskop.controller;

import com.interview.bioskop.model.Orders;
import com.interview.bioskop.model.Tickets;
import com.interview.bioskop.repository.OrdersRepository;
import com.interview.bioskop.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrdersRepository orderRepository;

    @Autowired
    TicketsRepository ticketRepository;

    @PostMapping("/post")
    int createOrder(@RequestBody Orders order) {
        orderRepository.save(order);
        Optional<Tickets> tiket = ticketRepository.findById(order.getTicketId());
        if(tiket.isPresent())
        {
            tiket.get().setQuantity(tiket.get().getQuantity()-order.getBuy());
            ticketRepository.save(tiket.get());
        }
        return order.getOrderId();
    }
}
