package com.example.customerdemo.controller;

import com.example.customerdemo.dto.CustomerDto;
import com.example.customerdemo.entity.Customer;
import com.example.customerdemo.response.GenericResponse;
import com.example.customerdemo.service.CustomerService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customer")
public class CustomerController {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(dozerBeanMapper.map(customerService.getCustomerById(id), Customer.class));
    }

//    @GetMapping("customers")
//    public  ResponseEntity<Page<Customer>> getCustomers(Pageable pageable){
//        Page<CustomerDto> customerDtoList = customerService.getCustomers(pageable);
//        return ResponseEntity.ok(customerDtoList.map(customerDto ->
//                dozerBeanMapper.map(customerDto,Customer.class)));
//    }

    @GetMapping("customers/sort")
    public ResponseEntity<Page<Customer>> getCustomersWithSort(Pageable pageable, @RequestParam Boolean isDesc, @RequestParam String sortField) {
        Page<CustomerDto> customerDtoList = customerService.getCustomersWithSort(pageable, isDesc, sortField);
        return ResponseEntity.ok(customerDtoList.map(customerDto ->
                dozerBeanMapper.map(customerDto, Customer.class)));
    }

    @GetMapping("customers")
    public  ResponseEntity<List<Customer>> getCustomers(){
        List<CustomerDto> customerDtoList = customerService.getCustomers();
        return ResponseEntity.ok(customerDtoList.stream().map(customerDto ->
                dozerBeanMapper.map(customerDto,Customer.class)).collect(Collectors.toList()));
    }

    @GetMapping("location")
    public ResponseEntity<Page<Customer>> getCustomersByLocation(@RequestParam String location, Pageable pageable) {
        Page<CustomerDto> customerDtoList = customerService.getCustomersByLocation(location, pageable);
        return ResponseEntity.ok(customerDtoList.map(customerDto ->
                dozerBeanMapper.map(customerDto, Customer.class)));
    }

    @PostMapping("add-customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws IOException {
        CustomerDto customerDto = dozerBeanMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok(dozerBeanMapper.map(customerService.addCustomer(customerDto), Customer.class));
    }

    @DeleteMapping("delete-customer/{id}")
    public GenericResponse deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new GenericResponse("Customer Deleted");
    }

}