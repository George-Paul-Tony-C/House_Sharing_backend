package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
