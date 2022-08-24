package com.example.customerdemo.serviceImpl;

import com.example.customerdemo.dto.CustomerDto;
import com.example.customerdemo.entity.Customer;
import com.example.customerdemo.repository.CustomerRepository;
import com.example.customerdemo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            log.error("müşteri bulunamadı");
        }
        Customer customer = null;

        try {
            customer = customerRepository.findById(id).orElseThrow(() -> new IOException("Kullanıcı kayıtlı"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(id +" id'li müşteri başarıyla getirildi");
        return dozerBeanMapper.map(customer, CustomerDto.class);

    }

//    @Override
//    public Page<CustomerDto> getCustomers(Pageable pageable){
//        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().isEmpty() ? Sort.by("name").ascending() : pageable.getSort());
//        Page<Customer> customerList= customerRepository.findAll(customPageable);
//        return customerList.map(customer ->
//                dozerBeanMapper.map(customer,CustomerDto.class));
//    }


    public Page<CustomerDto> getCustomersWithSort(Pageable pageable, Boolean isDesc, String sortField) {


        Sort sort = isDesc ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();

        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Customer> customerList = customerRepository.findAll(customPageable);
        return customerList.map(customer ->
                dozerBeanMapper.map(customer, CustomerDto.class));
    }

    public List<CustomerDto> getCustomers(){
        List<Customer> customerList= customerRepository.findAll();
        return customerList.stream().map(customer ->
                dozerBeanMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<CustomerDto> getCustomersByLocation(String location, Pageable pageable) {
        Pageable customPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().isEmpty() ? Sort.by("name").ascending() : pageable.getSort());
        Page<Customer> customersByLocationList = customerRepository.findCustomersByLocation(location, customPageable);

        return customersByLocationList.map(customer ->
                dozerBeanMapper.map(customer, CustomerDto.class));
    }


    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) throws IOException {
        List<CustomerDto> customerDtoList = getCustomers();
        String customerDtoName = customerDto.getName();
        if (isCustomerNameExists(customerDtoName, customerDtoList)) {
            log.error("Kullanıcı adı kayıtlı");
            throw new IOException("Kullanıcı adı kayıtlı");
        }
//        customerDto.setName(customerDto.getName().toLowerCase().trim());
//        customerDto.setSurname(customerDto.getSurname().toLowerCase().trim());
//        customerDto.setLocation(customerDto.getLocation().toLowerCase().trim());
        Customer customerReturned = customerRepository.save(dozerBeanMapper.map(customerDto, Customer.class));
        log.info("Müşteri eklendi");
        return dozerBeanMapper.map(customerReturned, CustomerDto.class);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            log.error("Müşteri silinemedi!!!");
        }
        customerRepository.deleteById(id);
        log.info("Müşteri silindi");
    }

    public boolean isCustomerNameExists(String customerName, List<CustomerDto> customerDtoList) {
        for (CustomerDto customerDto : customerDtoList) {
            if (customerDto.getName().equals(customerName)) {
                return true;
            }
        }
        return false;
    }

}
