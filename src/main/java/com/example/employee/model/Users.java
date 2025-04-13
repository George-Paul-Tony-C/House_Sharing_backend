package com.example.employee.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
@JsonIgnoreProperties({"houses", "reviews", "bookings"})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    @Column(unique = true)
    private String email;
    
    private Long ph_no;
    private String password;
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Houses> houses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Bookings> bookings;
}
