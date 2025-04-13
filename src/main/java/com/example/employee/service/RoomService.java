package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Rooms;
import com.example.employee.repository.RoomRepository;

@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepo;

    public Rooms saveRooms(Rooms room){
        return roomRepo.save(room);
    }

    public List<Rooms> getRoomsByHouseId(Integer houseId){
        return roomRepo.findByHouse_HouseId(houseId);
    }
}
