package com.example.springboot.back.customer.web;

import com.example.springboot.back.customer.entity.Customer;
import com.example.springboot.back.customer.entity.CustomerRepository;
import com.example.springboot.back.customer.web.dtos.CustomerDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    /**
     * 목록 가져오기
     */
    public List<CustomerDto> getCustomerList() {
        List<Customer> customerEntities = customerRepository.findAll();
        List<CustomerDto> dtos = new ArrayList<>();

        for (Customer entity : customerEntities) {
            CustomerDto dto = CustomerDto.builder()
                    .cor_reg_no(entity.getCor_reg_no())
                    .customer_name(entity.getCustomer_name())
                    .typeCode(entity.getTypeCode())
                    .email(entity.getEmail())
                    .region1(entity.getRegion1())
                    .region2(entity.getRegion2())
                    .fax(entity.getFax())
                    .web(entity.getWeb())
                    .address1(entity.getAddress1())
                    .address2(entity.getAddress2())
                    .high_customer(entity.getHigh_customer())
                    .note(entity.getNote())
                    .tel(entity.getTel())
                    .semail(entity.getSemail())
                    .content_id(entity.getContent_id())
                    .content_pw(entity.getContent_pw())
                    .build();

            dtos.add(dto);
        }

        return dtos;
    }
     /**
     * 단건 가져오기
     */
    public CustomerDto getCustomer(String id) {
        Customer entity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));
        return CustomerDto.builder()
                .cor_reg_no(entity.getCor_reg_no())
                .customer_name(entity.getCustomer_name())
                .typeCode(entity.getTypeCode())
                .email(entity.getEmail())
                .region1(entity.getRegion1())
                .region2(entity.getRegion2())
                .fax(entity.getFax())
                .web(entity.getWeb())
                .address1(entity.getAddress1())
                .address2(entity.getAddress2())
                .high_customer(entity.getHigh_customer())
                .note(entity.getNote())
                .tel(entity.getTel())
                .semail(entity.getSemail())
                .content_id(entity.getContent_id())
                .content_pw(entity.getContent_pw())
                .build();
    }
}
