package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Houses;
import com.example.employee.service.HouseService;

@RestController
@RequestMapping( path = "/api/house")
public class HouseController {
    
    @Autowired
    private HouseService houseService;

    @PostMapping("/add")
    public Houses inser(@RequestBody Houses houses){
        return houseService.saveHouse(houses);
    }
}
