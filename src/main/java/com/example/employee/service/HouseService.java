package com.example.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.model.Houses;
import com.example.employee.model.Reviews;
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

    public List<Rooms> getRoomsForHouse(Integer houseId){
        Houses house = houseRepo.findById(houseId).orElse(null);
        if (house != null) {
            return house.getRooms() != null ? house.getRooms() : List.of();
        }
        return List.of(); 
    }

    public List<Reviews> getReviewofAllRooms(Integer houseId){

        Houses house = houseRepo.findById(houseId).orElse(null);

        if(house != null && house.getRooms() != null ){

            List<Reviews> AllReview = new ArrayList<>();
            
            for(Rooms room : house.getRooms()){
                if(room.getReviews() != null){
                    AllReview.addAll(room.getReviews());
                }
            }

            return AllReview;

        }
        return List.of();
    }

    public Houses updateHouse(Integer houseId, Houses updatedHouse) {
        return houseRepo.findById(houseId).map(house -> {
            house.setNoOfRooms(updatedHouse.getNoOfRooms());
            house.setImageUrl(updatedHouse.getImageUrl());
            house.setAmenities(updatedHouse.getAmenities());
            house.setAddress(updatedHouse.getAddress());
            house.setDescription(updatedHouse.getDescription());
            house.setLocation(updatedHouse.getLocation());
            // user and rooms are not updated here to avoid unwanted side effects
            return houseRepo.save(house);
        }).orElse(null);
    }

}
