package com.example.customerdemo.service;

import com.example.customerdemo.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto getCustomerById(Long id);
    List<CustomerDto> getCustomers();
    CustomerDto addCustomer(CustomerDto customerDto);
    void deleteCustomerById(Long id);
}
