package com.example.customerdemo.repository;

import com.example.customerdemo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

     Page<Customer> findCustomersByLocation(String location, Pageable pageable);
}
