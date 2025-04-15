package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Reviews;
import com.example.employee.model.Rooms;
import com.example.employee.service.RoomService;

@RestController
@RequestMapping(path = "/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public Rooms insert(@RequestBody Rooms room) {
        return roomService.saveRooms(room);
    }

    @GetMapping("/{roomId}")
    public Rooms getCurrentRooms(@PathVariable("roomId") Integer roomId) {
        return roomService.getCurrentRoom(roomId);
    }

    @GetMapping("/house/{houseId}")
    public List<Rooms> getAllRoomsOfHouse(@PathVariable("houseId") Integer houseId) {
        return roomService.getRoomsByHouseId(houseId);
    }

    @GetMapping("/reviews/{roomId}")
    public List<Reviews> getAllReviewsOfRooms(@PathVariable("roomId") Integer roomId) {
        return roomService.getAllReviewByRoomId(roomId);
    }

    @DeleteMapping("/delete/{roomId}")
public ResponseEntity<String> deleteRoom(@PathVariable Integer roomId) {
    return roomService.deleteRoom(roomId);
  }

    @PutMapping("/update/{roomId}")
public ResponseEntity<?> updateRoom(@PathVariable("roomId") Integer roomId, @RequestBody Rooms updatedRoom) {
    Rooms result = roomService.updateRoom(roomId, updatedRoom);
    if (result != null) {
        return ResponseEntity.ok(result);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
    }
}

}
