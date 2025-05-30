package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Rooms;


@Repository
public interface RoomRepository extends JpaRepository<Rooms, Integer>{
    List<Rooms> findByHouse_HouseId(Integer houseId);
}