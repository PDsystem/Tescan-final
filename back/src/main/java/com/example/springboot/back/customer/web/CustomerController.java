package com.example.springboot.back.customer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.springboot.back.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

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
    // public Header<List<CustomerDto>> customerList(Pageable pageable,HttpServletRequest request) { 
    public Header<List<CustomerDto>> customerList(@PageableDefault(sort = {"regno"}) Pageable pageable,HttpServletRequest request) { 
        HttpSession session = request.getSession();
        // System.out.print(pageable.getPageNumber());
        log.info("1");
        return customerService.getCustomerList(pageable); }

    @GetMapping("/customer/{id}")
    public CustomerDto getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id);
    }
}
