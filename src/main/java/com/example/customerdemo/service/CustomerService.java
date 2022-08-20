package com.example.customerdemo.service;

import com.example.customerdemo.dto.CustomerDto;
import com.example.customerdemo.entity.Customer;
import com.example.customerdemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @Autowired
    CustomerRepository customerRepository;

    public CustomerDto getCustomerById(Long id){
        if(!customerRepository.existsById(id)){
            log.error("customer not found");
        }
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        log.info("getCustomerById is successful");
        return dozerBeanMapper.map(customer,CustomerDto.class);

    }

    public List<CustomerDto> getCustomers(){
            List<Customer> customerList= customerRepository.findAll();

        return customerList.stream().map(customer ->
                dozerBeanMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        List<CustomerDto> customerDtoList = getCustomers();
        String customerDtoName = customerDto.getName();
        if(isCustomerNameExists(customerDtoName,customerDtoList)){
            log.error("user already exists");
            throw new RuntimeException("user already exists");
        }
        Customer customerReturned = customerRepository.save(dozerBeanMapper.map(customerDto,Customer.class));
        log.info("customer added");
        return dozerBeanMapper.map(customerReturned,  CustomerDto.class);
    }

    public void deleteCustomerById(Long id){
        if(!customerRepository.existsById(id)){
            log.error("deleteCustomerById error");
        }
        customerRepository.deleteById(id);
        log.info("deleteCustomerById is successful");
    }

    public boolean isCustomerNameExists(String customerName, List<CustomerDto> customerDtoList){
        for (CustomerDto customerDto : customerDtoList) {
            if(customerDto.getName().equals(customerName)){
                return true;
            }
        }
        return false;
    }

}
