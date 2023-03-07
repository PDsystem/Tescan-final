package com.example.springboot.back.customer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.*;

// import com.example.springboot.back.customer.entity.TS_CUSTOMER;
import com.example.springboot.back.customer.web.dtos.CustomerDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class CustomerController {
    
    private final CustomerService customerService;

    @GetMapping("/customer/list")
    public List<CustomerDto> customerList(HttpServletRequest request) { 
        HttpSession session = request.getSession();
       
        return customerService.getCustomerList(); }

    @GetMapping("/customer/{id}")
    public CustomerDto getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id);
    }
}
