package com.example.customerdemo.service;

import com.example.customerdemo.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    CustomerDto getCustomerById(Long id);
    Page<CustomerDto> getCustomers(Pageable pageable);
    Page<CustomerDto> getCustomersByLocation(String location,Pageable pageable);
    CustomerDto addCustomer(CustomerDto customerDto,Pageable pageable) throws IOException;
    void deleteCustomerById(Long id);
}
