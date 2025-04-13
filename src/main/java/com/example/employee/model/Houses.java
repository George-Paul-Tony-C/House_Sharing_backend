package com.example.employee.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Table(name = "houses")
@JsonIgnoreProperties({"rooms"})
public class Houses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer houseId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private Integer noOfRooms;

    private String imageUrl;

    private String amenities;

    private String address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Rooms> rooms;
}
