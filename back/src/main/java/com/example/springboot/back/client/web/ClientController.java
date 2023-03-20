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
    @PostMapping("/client/{x}")
    public ClientDto getClient(@PathVariable String x) {
        return clientService.getClient(x);
    }

    @PostMapping("/client")
    public void create(@RequestBody ClientDto clientDto) {
        clientService.create(clientDto);
    }

    @PatchMapping("/client")
    public void update(@RequestBody ClientDto clientDto) {
        System.out.println(clientDto.getCor_reg_no());
        clientService.update(clientDto);
    }

    @DeleteMapping("/client/{id}")
    public void delete(@PathVariable String id) {
        clientService.delete(id);
    }
}
