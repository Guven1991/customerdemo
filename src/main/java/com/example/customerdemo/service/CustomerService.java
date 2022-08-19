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
        Customer customer = customerRepository.findById(id).orElse(null);
        log.info("getCustomerById is successful");
        return dozerBeanMapper.map(customer,CustomerDto.class);

    }

    public List<CustomerDto> getCustomers(){
            List<Customer> customerList= customerRepository.findAll();

        return customerList.stream().map(customer ->
                dozerBeanMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customerReturned = customerRepository.save(dozerBeanMapper.map(customerDto,Customer.class));
        return dozerBeanMapper.map(customerReturned,  CustomerDto.class);
    }

    public void deleteCustomerById(Long id){

        customerRepository.deleteById(id);
        log.info("deleteCustomerById is successful");
    }

}
