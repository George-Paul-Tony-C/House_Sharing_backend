package com.example.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Houses;
import com.example.employee.model.Reviews;
import com.example.employee.model.Rooms;
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

    @PostMapping("/update/{houseId}")
    public ResponseEntity<?> updateHouse(@PathVariable("houseId") Integer houseId, @RequestBody Houses updatedHouse) {
        Houses result = houseService.updateHouse(houseId, updatedHouse);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("House not found");
        }
    }
    @GetMapping("/allhouses")
    public List<Houses> getAllHouses(){
        return houseService.getAllHouses();
    }

    @DeleteMapping("/delete/{houseId}")
    public ResponseEntity<String> deleteHouse(@PathVariable("houseId") Integer houseId) {
        return houseService.deleteHouse(houseId);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<Houses> getCurrentHouse(@PathVariable("houseId") Integer houseId){
        Houses house = houseService.getHouseById(houseId);
        if (house != null) {
            return ResponseEntity.ok(house);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/rooms/{houseId}")
    public ResponseEntity<?> getRoomsForHouse(@PathVariable("houseId") Integer houseId) {
        List<Rooms> rooms = houseService.getRoomsForHouse(houseId);
        if (rooms != null && !rooms.isEmpty()) {
            Map<String , Object> response = new HashMap<>();
            response.put("message" , "Rooms Data fetched");
            response.put("rooms", rooms);
            
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
    }


    @GetMapping("/reviews/{houseId}")
    public List<Reviews> getReviewsOfHouse(@PathVariable("houseId") Integer houseId){
        return houseService.getReviewofAllRooms(houseId);
    }

}
