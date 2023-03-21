package com.example.springboot.back.client.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.springboot.back.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.springboot.back.client.entity.Client;
import com.example.springboot.back.client.web.dtos.ClientDto;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class ClientController {
    
    private final ClientService clientService;

    @GetMapping("/client/list")
    public Header<List<ClientDto>> clientList(@PageableDefault(sort = {"corRegNo"}) Pageable pageable
                                                    ,HttpServletRequest request,String keyword,String searchType) { 
        HttpSession session = request.getSession();
        return clientService.getClientList(pageable,keyword,searchType); 
    }
    @PostMapping("/client/{corRegNo}/{cusNo}")
    public ClientDto getClient(@PathVariable String corRegNo,@PathVariable String cusNo) {
        log.info("controller : "+corRegNo);
        log.info("controller : "+cusNo);
        return clientService.getClient(corRegNo,cusNo);
    }//corRegNo,cusNo는 컨트롤러 내에서만 같은 것을 부르면 됩니다.(다른것과 변수명이 다르다고 큰 차이가 생기진 않음)

    @PostMapping("/client")
    public void create(@RequestBody ClientDto clientDto) {
        System.out.println(clientDto.getCor_reg_no());
    
        System.out.println(clientDto.getCus_no());
        clientService.create(clientDto);
    }
    
    @PatchMapping("/client")
    public void update(@RequestBody ClientDto clientDto) {
        clientService.update(clientDto);
    }

    @DeleteMapping("/client/{corRegNo}/{cusNo}")
    public void delete(@PathVariable String corRegNo,@PathVariable String cusNo) { 
        clientService.delete(corRegNo,cusNo);
    }
}
