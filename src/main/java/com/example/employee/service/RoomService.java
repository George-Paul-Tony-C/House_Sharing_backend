package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.employee.model.Reviews;
import com.example.employee.model.Rooms;
import com.example.employee.repository.RoomRepository;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepo;

    public Rooms saveRooms(Rooms room){
        return roomRepo.save(room);
    }

    public Rooms getCurrentRoom(Integer roomId){
        return roomRepo.findById(roomId).orElse(null);
    }

    public List<Rooms> getRoomsByHouseId(Integer houseId){
        return roomRepo.findByHouse_HouseId(houseId);
    }

    public List<Reviews> getAllReviewByRoomId(Integer roomId){
        Rooms room = roomRepo.findById(roomId).orElse(null);
        if(room != null){
            return room.getReviews() != null ? room.getReviews() : List.of();
        }
        return List.of();
    }

    // Update Room by ID
    public Rooms updateRoom(Integer roomId, Rooms updatedRoom) {
        return roomRepo.findById(roomId).map(room -> {
            room.setRoomName(updatedRoom.getRoomName());
            room.setRoomPrice(updatedRoom.getRoomPrice());
            room.setAvailable(updatedRoom.getAvailable());
            room.setRoomCapacity(updatedRoom.getRoomCapacity());
            room.setRoomType(updatedRoom.getRoomType());
            room.setImageUrl(updatedRoom.getImageUrl());
    
            // Optional: update house only if houseId is valid and changing
            if (updatedRoom.getHouse() != null && updatedRoom.getHouse().getHouseId() != null) {
                room.setHouse(updatedRoom.getHouse());
            }
    
            return roomRepo.save(room);
        }).orElse(null);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<String> deleteRoom(Integer roomId) {
    if (roomRepo.existsById(roomId)) {
        roomRepo.deleteById(roomId);
        return ResponseEntity.ok("Room deleted successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
    }
}
    

}
