package com.example.customerdemo.controller;

import com.example.customerdemo.dto.CustomerDto;
import com.example.customerdemo.entity.Customer;
import com.example.customerdemo.service.CustomerService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CustomerController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(dozerBeanMapper.map(customerService.getCustomerById(id),Customer.class));
    }

    @GetMapping
    public  ResponseEntity<List<Customer>> getCustomers(){
        List<CustomerDto> customerDtoList = customerService.getCustomers();
        return ResponseEntity.ok(customerDtoList.stream().map(customerDto ->
                dozerBeanMapper.map(customerDto,Customer.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public  ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        CustomerDto customerDto = dozerBeanMapper.map(customer,CustomerDto.class);
        return ResponseEntity.ok(dozerBeanMapper.map(customerService.addCustomer(customerDto), Customer.class));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }

}
