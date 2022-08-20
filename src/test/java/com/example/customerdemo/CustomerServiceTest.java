package com.example.customerdemo;

import com.example.customerdemo.dto.CustomerDto;
import com.example.customerdemo.entity.Customer;
import com.example.customerdemo.repository.CustomerRepository;
import com.example.customerdemo.service.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    Customer customer;
    CustomerDto customerDto;
    @Before
    public void init(){

        customer = new Customer();
        customer.setId(1L);
        customer.setName("ali");
        customer.setSurname("duru");
        customer.setLocation("istanbul");

        customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setLocation(customer.getLocation());

    }

    @Test
    public void getCustomerById(){
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        CustomerDto customerDto = customerServiceImpl.getCustomerById(1L);

        assertEquals(Optional.of(1L),Optional.ofNullable(customerDto.getId()));
        assertEquals("ali",customerDto.getName());
        assertEquals("duru",customerDto.getSurname());
        assertEquals("istanbul",customerDto.getLocation());
    }

    @Test
    public void getCustomers(){
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        List<CustomerDto> customerList = customerServiceImpl.getCustomers();
        assertEquals(1,customerList.size());
        assertEquals("ali",customerList.get(0).getName());

    }

    @Test
    public void addCustomer(){
        when(customerRepository.save(any())).thenReturn(customer);
        CustomerDto returnedCustomerDto = customerServiceImpl.addCustomer(customerDto);
        assertEquals(Optional.of(1L), Optional.ofNullable(returnedCustomerDto.getId()));
        assertEquals("ali",returnedCustomerDto.getName());
        assertEquals("duru",returnedCustomerDto.getSurname());
    }

    @Test
    public void deleteCustomerById(){
        when(customerRepository.existsById(any())).thenReturn(true);
        customerServiceImpl.deleteCustomerById(1L);
        verify(customerRepository).deleteById(1L);
    }

}
