package com.interview.bioskop.repository;

import com.interview.bioskop.model.Customers;
import com.interview.bioskop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {

}
