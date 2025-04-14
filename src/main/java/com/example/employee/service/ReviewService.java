package com.example.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee.model.Reviews;
import com.example.employee.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepo;

    public Reviews savReviews(Reviews review){
        return reviewRepo.save(review);
    }

    public List<Reviews> getAllReview(){
        return reviewRepo.findAll();
    }
}
