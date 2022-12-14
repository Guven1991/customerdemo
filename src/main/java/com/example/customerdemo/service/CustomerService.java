package com.example.customerdemo.service;

import com.example.customerdemo.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    CustomerDto getCustomerById(Long id);

    //    Page<CustomerDto> getCustomers(Pageable pageable);
    Page<CustomerDto> getCustomersWithSort(Pageable pageable, Boolean isDesc, String sortField);

    List<CustomerDto> getCustomers();
    Page<CustomerDto> getCustomersByLocation(String location, Pageable pageable);

    CustomerDto addCustomer(CustomerDto customerDto) throws IOException;

    void deleteCustomerById(Long id);
}
