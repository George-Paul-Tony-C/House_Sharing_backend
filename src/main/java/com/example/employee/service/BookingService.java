package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Bookings;
import com.example.employee.model.Bookings.StatusType;
import com.example.employee.model.Rooms;
import com.example.employee.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private RoomService roomService;

    public Bookings saveBookings(Bookings booking) {
        Rooms room = roomService.getCurrentRoom(booking.getRoom().getRoomId());
        
        if (room != null && Boolean.TRUE.equals(room.getAvailable())) {
            // Check for overlapping bookings
            List<Bookings> existingBookings = bookingRepo.findByRoomRoomId(room.getRoomId());
            
            boolean hasOverlap = existingBookings.stream()
                .filter(b -> b.getStatus() == StatusType.BOOKED) // only check active bookings
                .anyMatch(existingBooking -> {
                    // Check if the new booking overlaps with any existing booking
                    return (booking.getStartDate().before(existingBooking.getEndDate()) && 
                            booking.getEndDate().after(existingBooking.getStartDate()));
                });
            
            if (hasOverlap) {
                return null; // Or throw a custom exception for clearer error handling
            }
            
            booking.setStatus(StatusType.BOOKED);
            booking.setRoom(room);
            return bookingRepo.save(booking);
        }
        
        return null;
    }


    public Bookings cancelBooking(Integer bookingId) {
        Bookings booking = bookingRepo.findById(bookingId).orElse(null);
    
        if (booking != null && booking.getStatus() == Bookings.StatusType.BOOKED) {
            booking.setStatus(Bookings.StatusType.CANCELLED);
    
            Rooms room = booking.getRoom();
            if (room != null && !room.getAvailable()) {
                room.setAvailable(true);
                roomService.saveRooms(room);
            }
    
            return bookingRepo.save(booking);
        }
    
        return null; 
    }
    
}
