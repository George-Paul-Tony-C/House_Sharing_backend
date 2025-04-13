package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email); 
    boolean existsByEmail(String email);
}
