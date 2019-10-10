package com.interview.bioskop.controller;

import com.interview.bioskop.exception.ResourceNotFoundException;
import com.interview.bioskop.model.Customers;
import com.interview.bioskop.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomersRepository customerRepository;


    @GetMapping
    ResponseEntity<List<Customers>> getCustomers(){
        return new ResponseEntity<List<Customers>>((List<Customers>) customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customers> getCustomerById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        Customers customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this ID : " + id));
        return ResponseEntity.ok().body(customer);
    }
}
