package com.example.employee.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "rooms")
@JsonIgnoreProperties({"reviews","bookings"})
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private Houses house;

    @Column(unique = true)
    private String roomName;

    private Double roomPrice;

    private Boolean available;

    private Integer roomCapacity;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Bookings> bookings;

    private String imageUrl;

    public enum RoomType {
        AC,
        NON_AC,
        DELUXE,
        PREMIUM
    }
}
