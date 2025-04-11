package com.example.employee.model;

import java.util.List;

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
public class Houses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer houseId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;

    private Integer noOfRooms;

    private String imageUrl;

    private String amenities;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Rooms> rooms;
}
