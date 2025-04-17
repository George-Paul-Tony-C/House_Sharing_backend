package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<?> addBooking(@RequestBody Bookings booking) {
        try {
            Bookings savedBooking = bookingService.saveBookings(booking);
            if (savedBooking != null) {
                return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Room not available or booking dates conflict with existing bookings", 
                                            HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
        Bookings cancelled = bookingService.cancelBooking(bookingId);

        if (cancelled != null) {
            return ResponseEntity.ok("Booking Cancelled");
        } else {
            return ResponseEntity.badRequest().body("Booking not found or already cancelled.");
        }
    }

    @GetMapping("/rooms/{userId}")
    public ResponseEntity<List<Bookings>> getBookingsByOwner(@PathVariable Integer userId) {
        List<Bookings> bookings = bookingService.getBookingsOfRoomsByUser(userId);
        return ResponseEntity.ok(bookings);
    }

}
