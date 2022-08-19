package com.example.customerdemo.service;

import com.example.customerdemo.entity.Customer;
import com.example.customerdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerById(Long id){

        return  customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getCustomers(){

        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id){
        customerRepository.deleteById(id);
    }

}
