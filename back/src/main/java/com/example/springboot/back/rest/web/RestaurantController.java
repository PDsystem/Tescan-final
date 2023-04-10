package com.example.springboot.back.rest.web;

import lombok.RequiredArgsConstructor;

import com.example.springboot.back.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import com.example.springboot.back.rest.web.dtos.RestDto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class RestaurantController {

    private final RestaurantService restService;
 
    @GetMapping("/restaurant/list")
    public Header<List<RestDto>> restList(@PageableDefault(sort = {"restNo"}) Pageable pageable
                                                    // ) { 
                                                    ,HttpServletRequest request,String keyword,String searchType) { 
                                                    // String keyword=null;
                                                    // String searchType=null;
        // HttpSession session = request.getSession();

        return restService.getRestList(pageable,keyword,searchType); 
    }
    @PostMapping("/restaurant/{x}")
    public RestDto getRest(@PathVariable Integer x) {
        return restService.getRest(x);
    }
    // @PostMapping("/restaurant/random")
    @PostMapping("/restaurant/random")
    public RestDto getRandomRest() {
        return restService.getRandomRest();
    }
    // @PostMapping("/restaurant")
    // public void create(@RequestBody RestDto restDto) {
    //     restService.create(restDto);
    // }

    @PatchMapping("/restaurant")
    public void update(@RequestBody RestDto restDto) {
        System.out.println(restDto.getRest_no());
        restService.update(restDto);
    }
    @DeleteMapping("/restaurant/{id}")
    public void delete(@PathVariable Integer id) {
        restService.delete(id);
    }
    
}