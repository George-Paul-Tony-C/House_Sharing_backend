package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Bookings;
import com.example.employee.service.BookingService;

@RestController
@RequestMapping(path = "/api/booking")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public Bookings insert(@RequestBody Bookings bookings){
        return bookingService.saveBookings(bookings);
    }
}
