package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Bookings;
import com.example.employee.model.Bookings.StatusType;
import com.example.employee.model.Rooms;
import com.example.employee.repository.BookingRepository;
import com.example.employee.repository.RoomRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepo;

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

    // @Scheduled(fixedRate = 1000)
    // public void updateRoomAvailability() {
    //     Date today = new Date(); // current date and time
    //     List<Rooms> allRooms = roomRepo.findAll();

    //     for (Rooms room : allRooms) {
    //         List<Bookings> bookings = bookingRepo.findByRoomRoomId(room.getRoomId());

    //         boolean isCurrentlyBooked = bookings.stream()
    //             .anyMatch(booking -> {
    //                 // Check if the current time is within the booking's start and end time
    //                 return booking.getStatus() == StatusType.BOOKED && 
    //                     (today.after(booking.getStartDate()) && today.before(booking.getEndDate()) || 
    //                     today.equals(booking.getStartDate()) || today.equals(booking.getEndDate()));
    //             });

    //         // Update the availability based on the current bookings
    //         room.setAvailable(!isCurrentlyBooked);
    //         roomRepo.save(room);
    //     }
    // }


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
