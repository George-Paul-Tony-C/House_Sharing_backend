package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Reviews;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Integer>{
    
}
