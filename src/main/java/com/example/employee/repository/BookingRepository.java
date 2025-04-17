package com.example.employee.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Bookings;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer>{
    List<Bookings> findByEndDateBeforeAndStatus(Date date, Bookings.StatusType status);
    List<Bookings> findByStartDateBeforeAndEndDateAfterAndStatus(Date now1, Date now2, Bookings.StatusType status);

    List<Bookings> findByRoomRoomId(Integer roomId);

    // BookingRepository.java
    @Query("SELECT b FROM Bookings b WHERE b.room.house.user.userId = :userId")
    List<Bookings> findBookingsByRoomOwner(@Param("userId") Integer userId);


}
