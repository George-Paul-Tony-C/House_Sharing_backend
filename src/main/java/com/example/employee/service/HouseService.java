package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.model.Houses;
import com.example.employee.model.Rooms;
import com.example.employee.repository.HouseRepository;

@Service
public class HouseService {
    
    @Autowired
    private HouseRepository houseRepo;

    public Houses saveHouse(Houses house){
        return houseRepo.save(house);
    }

    public Houses getHouseById(Integer houseId){
        return houseRepo.findById(houseId).orElse(null); // Return null if house not found
    }

    public List<Houses> getAllHouses(){
        return houseRepo.findAll();
    }

    public ResponseEntity<String> deleteHouse(Integer houseId){
        Optional<Houses> house = houseRepo.findById(houseId);

        if(house.isPresent()){
            houseRepo.deleteById(houseId);
            return ResponseEntity.ok("House Deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("House not found");
    }

    // Fix: Return empty list if no rooms found
    public List<Rooms> getRoomsForHouse(Integer houseId){
        Houses house = houseRepo.findById(houseId).orElse(null);
        if (house != null) {
            // Return the list of rooms or an empty list if no rooms exist
            return house.getRooms() != null ? house.getRooms() : List.of();
        }
        return List.of(); // Return an empty list if the house doesn't exist
    }
}
