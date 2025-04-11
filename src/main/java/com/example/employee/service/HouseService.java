package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Houses;
import com.example.employee.repository.HouseRepository;

@Service
public class HouseService {
    
    @Autowired
    private HouseRepository houseRepo;

    public Houses savHouses(Houses house){
        return houseRepo.save(house);
    }
}
