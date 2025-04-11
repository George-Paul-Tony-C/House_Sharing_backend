package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Rooms;
import com.example.employee.service.RoomService;

@RestController
@RequestMapping(path = "/api/room")
public class RoomController {
    
    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public Rooms insert(@RequestBody Rooms room){
        return roomService.saveRooms(room);
    }
}
