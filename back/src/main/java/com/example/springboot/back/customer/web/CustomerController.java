package com.example.springboot.back.customer.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.springboot.back.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.springboot.back.customer.entity.Customer;
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
    public Header<List<CustomerDto>> customerList(@PageableDefault(sort = {"corRegNo"}) Pageable pageable
                                                    ,HttpServletRequest request,String keyword) { 
        HttpSession session = request.getSession();
        // System.out.print(pageable.getPageNumber());
        log.info("단건 검색 들어왔나요?"+x);
        return customerService.getCustomerList(pageable,keyword); 
    }

    @PostMapping("/customer/{x}")
    public CustomerDto getCustomer(@PathVariable String x) {
        return customerService.getCustomer(x);
    }

    @PostMapping("/customer")
    public void create(@RequestBody CustomerDto customerDto) {
        customerService.create(customerDto);
    }

    @PatchMapping("/customer")
    public void update(@RequestBody CustomerDto customerDto) {
        customerService.update(customerDto);
    }

    @DeleteMapping("/customer/{id}")
    public void delete(@PathVariable String id) {
        customerService.delete(id);
    }
}
