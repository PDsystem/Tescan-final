package com.example.springboot.back.customer.web;

import com.example.springboot.back.customer.entity.Customer;
import com.example.springboot.back.customer.entity.CustomerRepository;
import com.example.springboot.back.customer.web.dtos.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.springboot.back.model.*;

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
    private final CustomerRepository repository;

    /**
     * 목록 가져오기
     */
    public Header<List<CustomerDto>> getCustomerList(Pageable pageable) {
        // Page<Customer> customerEntities = repository.selectByCustomer(pageable);
        Page<Customer> customerEntities = repository.findAllByOrderByCorRegNoDesc(pageable);

        List<CustomerDto> dtos = new ArrayList<>();
        log.info("2");
        for (Customer entity : customerEntities) {
            CustomerDto dto = CustomerDto.builder()
                    .cor_reg_no(entity.getCorRegNo())
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
        // log.info("3");
        Pagination pagination = new Pagination(
            (int) customerEntities.getTotalElements()
            , pageable.getPageNumber() + 1
            , pageable.getPageSize()
            , 10
         );
        //  log.info("4");
        return Header.OK(dtos, pagination);
    }
     /**
     * 단건 가져오기
     */
    public CustomerDto getCustomer(String id) {
        Customer entity = repository.findById(id).orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다."));
        return CustomerDto.builder()
                .cor_reg_no(entity.getCorRegNo())
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

    /**
     * 게시글 등록
     */
    public Customer create(CustomerDto customerDto) {
    
        Customer entity = Customer.builder()
                .corRegNo(customerDto.getCor_reg_no())
                .customer_name(customerDto.getCustomer_name())
                .typeCode(customerDto.getTypeCode())
                .email(customerDto.getEmail())
                .region1(customerDto.getRegion1())
                .region2(customerDto.getRegion2())
                .fax(customerDto.getFax())
                .web(customerDto.getWeb())
                .address1(customerDto.getAddress1())
                .address2(customerDto.getAddress2())
                .high_customer(customerDto.getHigh_customer())
                .note(customerDto.getNote())
                .tel(customerDto.getTel())
                .content_id(customerDto.getContent_id())
                .content_pw(customerDto.getContent_pw())
                // .createdAt(LocalDateTime.now())
                .build();
        return repository.save(entity);
    }

    /**
     * 게시글 수정
     */
    public Customer update(CustomerDto customerDto) {
        Customer entity = repository.findById(customerDto.getCor_reg_no()).orElseThrow(() -> new RuntimeException("거래처를 찾을 수 없습니다."));
            entity.setCorRegNo(customerDto.getCor_reg_no());
            entity.setCustomer_name(customerDto.getCustomer_name());
            entity.setTypeCode(customerDto.getTypeCode());
            entity.setEmail(customerDto.getEmail());
            entity.setRegion1(customerDto.getRegion1());
            entity.setRegion2(customerDto.getRegion2());
            entity.setFax(customerDto.getFax());
            entity.setWeb(customerDto.getWeb());
            entity.setAddress1(customerDto.getAddress1());
            entity.setAddress2(customerDto.getAddress2());
            entity.setHigh_customer(customerDto.getHigh_customer());
            entity.setNote(customerDto.getNote());
            entity.setTel(customerDto.getTel());
            entity.setSemail(customerDto.getSemail());
            entity.setContent_id(customerDto.getContent_id());
            entity.setContent_pw(customerDto.getContent_pw());
    
        return repository.save(entity);
    
    }

    /**
     * 게시글 삭제
     */
    public void delete(String id) {
        Customer entity = repository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        repository.delete(entity);
    
    }
}
