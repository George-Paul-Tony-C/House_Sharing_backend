package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Bookings;
import com.example.employee.repository.BookingRepository;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepo;

    public Bookings saveBookings(Bookings booking){
        return bookingRepo.save(booking);
    }
}
